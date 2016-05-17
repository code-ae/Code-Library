class Token:
	
	def __init__(self,strtoken)
		self.strtoken=strtoken
		self.type='unknown'
		
	def GetStringValue(self):
		return self.strtoken
	
	def GetElementType(self):
		return self.type

class LiteralIntToken(Token):
	typename='Literal Int'
		def __init__(self,strtoken)
			Token.__init__(self,strtoken)
			self.type=typename
			self.strtoken=strtoken
	
	def GetStringValue(self):
		return self.strtoken
	
	def GetElementType(self):
		return self.type
		
class NameToken(Token):
	typename='Name'
	def __init__(self,strtoken):
		Token.__init__(self,strtoken)	
		self.type=typename
		self.strtoken=strtoken
	
	def GetStringValue(self):
		return self.strtoken
	
	def GetElementType(self):
		return self.type
		