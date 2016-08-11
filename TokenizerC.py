class Token:
	
	def __init__(self,strtoken)     #constructor
		self.strtoken=strtoken  #property initialization
		self.type='unknown'     #sets variables to default values
		
	def GetStringValue(self):       #getter method
		return self.strtoken
	
	def GetElementType(self): 
		return self.type

class LiteralIntToken(Token):
	typename='Literal Int'
		def __init__(self,strtoken)		#constructor
			Token.__init__(self,strtoken)
			self.type=typename
			self.strtoken=strtoken
	
	def GetStringValue(self):
		return self.strtoken
	
	def GetElementType(self):
		return self.type
		
class NameToken(Token):
	typename='Name'
	def __init__(self,strtoken):   #constructor
		Token.__init__(self,strtoken)	
		self.type=typename
		self.strtoken=strtoken
	
	def GetStringValue(self):
		return self.strtoken
	
	def GetElementType(self):
		return self.type
		
