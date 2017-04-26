
 * Specs: Progam performs the first iteration of a macro pre processor. The program
 *        removed all macro definitions from a source code file and stores them in 
 *        a data structure. The program prints out the unique macro definitions to
 *        standard output and writes the source code to a separate file, with all
 *        of the macro definitions removed. 
 */  
#include <stdlib.h>
#include <map>
#include <fstream>
#include <sstream>
#include <string>
#include "file_parser.h"
#include "file_parse_exception.h"
#include "macroPP.h"
#include "macroPP_error_exception.h"

using namespace std;

//Globals
ofstream ofs;

/*
 * Constructor
 */
macroPP::macroPP(string fname){
    macro_start = 0;
    macro_end = 0;
    fp = new file_parser(fname);
    fp->read_file();
    process_macro_definitions();
    expand_macros(fname);
    cout << "Macro preprocessing complete." << endl;
}

/*
 * Deallocated all data that was created and stored on the
 * heap during runtime.
 */
macroPP::~macroPP() {
    delete fp;
    for(map<string,macro *>::iterator it = macrotab.begin(); it != macrotab.end(); ++it)
        macrotab.erase(it);
}

/*
 * Processes the source code file stored by the file parser and
 * removes and stores all macro definitons found in the parsed code.
 */
void macroPP::process_macro_definitions() {
    bool found_macro = find_macro_start(0);
    while(found_macro) {
        get_macro();
        fp->delete_rows(macro_start, macro_end + 1); 
        found_macro = find_macro_start(macro_start);
    }
}

/*
 * Creates a macro struct and stores all the definitions of the 
 * macro within the struct.
 */
void macroPP::get_macro() {
    string label, opcode, operand;
    unsigned int size = fp->size();
    macro *m = new macro;
    m->invocation_counter = 0;
    m->paramtab = get_parameters(fp->get_token(macro_start,2), ",");

    // If macro has already been defined, still need to find the endm
    // and delete the macro
    if(macrotab.count(fp->get_token(macro_start, LABEL)) != 0) {
        delete m;
        for(unsigned int i = macro_start; i < size; i++) {
            if(fp->get_token(i, OPCODE).compare("endm") == 0) {  
                macro_end = macro_start + (i - macro_start); 
                return;
            }
        }
        throw macroPP_error_exception("No valid end to macro definition");
    }

    // Populate the macro
    m->macro_name = fp->get_token(macro_start, LABEL); 
    for(unsigned int i = macro_start; i < size; i++) {
        if(fp->get_token(i, OPCODE).compare("endm") == 0) { 
            macro_end = macro_start + (i - macro_start); 
        break;
    }
        label = fp->get_token(i, LABEL); 
        opcode = fp->get_token(i, OPCODE);
        operand = fp->get_token(i, OPERAND);
        m->deftab.push_back(get_definitions(label, opcode, operand));
    }
    macrotab[m->macro_name] = m;
    return;
}

/*
 * Checks if a macro has been previously defined.
 * @param string, the name of the macro to check.
 * @return, true if the macro has been previously defined, false otherwise.
 */
bool macroPP::check_macro(string name) {
    map<string, macro *>::iterator it;
    for(it = macrotab.begin(); it != macrotab.end();++it)
        if(it->second->macro_name.compare(name) == 0)
            return true;
    return false;
}

/*
 * Creates a vector and stores three strings in it.
 * @param string, three string values to be stored in a vector.
 * @return vector<string>, the vector containing the string values.
 */
vector<string> macroPP::get_definitions(string l, string oc, string op){
    vector<string> vtmp;
    vtmp.push_back(l);
    vtmp.push_back(oc);
    vtmp.push_back(op);
    return vtmp;
}

/*
 * Searches for the start of a macro definitions. Returns true if a 
 * macro is found, false otherwise.
 * @return bool, returns true if a macro definition is found.
 */
bool macroPP::find_macro_start(int first) {
    if(first >= fp->size())
        return false;

    while(first < fp->size()) {
        string str = fp->get_token(first, OPCODE);
        if(str.compare("macro") == 0) { 
            macro_start = first;
            return true;
        }
        first++;
    }
    return false; 
}

/*
 * Returns vector containing the macro definition parameters
 */
vector<string> macroPP::get_parameters(string op, string delim){
    vector<string> vtmp;
    int last_pos = op.find_first_not_of(delim, 0);
    int curr_pos = op.find_first_of(delim, last_pos);

    while(last_pos != -1 || curr_pos != -1) {
        vtmp.push_back(op.substr(last_pos, curr_pos - last_pos));
        last_pos = op.find_first_not_of(delim, curr_pos);
        curr_pos = op.find_first_of(delim, last_pos);
    }
    return vtmp;
}

/*
 * Returns vector containing the macro call parameters
 */
vector<string> macroPP::get_ptab(string oc, string op) {
    vector<string> vt;
    vector<string> vex = get_parameters(oc, ".");

    if(vex.size() > 1)
        vt.push_back(vex.at(1));
    else
        vt.push_back("");
    vex.clear();
    vex = get_parameters(op, ",");

    for(unsigned int i = 0; i < vex.size(); i++)
        vt.push_back(vex.at(i));
    return vt;
}

void macroPP::expand_macros(string file_name) {
    const char *file = file_name.append("e").c_str();
    ofs.open(file);
    vector<string> vtmp;
    vector<vector<string> > vt;
    map<string, macro *>::iterator it;
    string l, oc, op, co, macro_name;

    for(int i = 0; i < fp->size(); i++){
        l = fp->get_token(i, LABEL);
        oc = fp->get_token(i, OPCODE);
        op = fp->get_token(i, OPERAND);
        co = fp->get_token(i, COMMENT);

        if(!oc.empty()) { 
            vtmp = get_parameters(oc, "."); 
            macro_name = vtmp[0];
            it = macrotab.find(macro_name);

            if(it != macrotab.end()) { 
                vtmp = get_ptab(oc, op); //get macro call parameters
                vt = it->second->deftab; //get macro defintion body

                if(l.compare("") != 0)
                    ofs << l << endl;
                ofs << "* " << l << " " << oc << " " << " " << op;

                //Print macro body
                for(unsigned int i = 1; i < vt.size(); ++i) 
                    sub_params(vtmp, it->second, i);
                
                it->second->invocation_counter++;
                ofs << "\n*\tEND OF MACRO EXPANSION\n" << endl;
                continue; 
            }
        }
        if(l.empty() && oc.empty() && op.empty())
            ofs << co << endl;
        else
            ofs << l << "\t"<< oc << "\t" << op << "\t" << co << endl;
    } 
    ofs.close();    
}

/*  
 * Substitute parameters into appropriate places
 */
void macroPP::sub_params(vector<string> ptable, macro *m, int index){
    vector<string> vtmp = m->deftab.at(index);
    unsigned int slash;
    string l = vtmp.at(0), oc = vtmp.at(1), op = vtmp.at(2);
    ostringstream counter;
    counter << "" << m->invocation_counter;

    slash = l.find("\\");
    if(slash < l.size())
        l.replace(slash, 2, counter.str());
    
    slash = oc.find("\\0");
    if(slash < oc.size())
        oc.replace(slash, 2, ptable[0]);

    for(unsigned int i = 0; i < op.size(); ++i){
        slash = op.find("\\", i);
        if(slash < op.size()) {
            if(op[slash + 1] == '@'){
                op.replace(slash, 2, counter.str());
                continue;
            }
            unsigned int a = op[slash + 1] - 48;
            if(a < ptable.size())
                op.replace(slash, 2, ptable[a]);
            else
                op.replace(slash, 2, "");
        }
    }
    ofs << "\n" << l << "\t"<< oc << "\t" << op << "\t";
}

/*
 * Returns the size of the container storing the macro definitions.
 * @return int, the number of macros stored.
 */
int macroPP::size() {
    return macrotab.size();
}

int main(int argc, char *argv[]) {

    if(argc !=2) {
        cout << "Usage: Invalid number of command line";
        cout << " arguments used" << endl;
        return 1;
    }
    try {
        macroPP processor(argv[1]);
    }                                    
    catch(macroPP_error_exception e) {
        cout << "Error: " << e.get_message() << endl;
        exit(1);
    }
    catch(file_parse_exception e) {
        cout << "Error: " << e.get_message() << endl;
        exit(1); 
    }
    return 0;
}

