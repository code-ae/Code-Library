package data_structures;

import java.util.BitSet;

public class BloomFilter {

	BitSet filter;
	int numbits;
	
	public BloomFilter(int size) {
		 
		 this.filter = new BitSet(size);
		 this.numbits = size;
		 this.filter.clear();
	 }
	 
	 
	 public void setBit(int bitIndex) {
		 this.filter.set(bitIndex);
	 }


	 public boolean getBit(int bitIndex) {
		 
		return this.filter.get(bitIndex); 
	 }
}

