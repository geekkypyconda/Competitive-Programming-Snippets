class INPUT:
	def __init__(this):
		pass
	def nextInt(this):
		return int(input())
	def nextFloat(this):
		return float(input())
	def nextLine(this):
		return input()

sc = INPUT()
def testCase():
	pass

def main():
	t = int(input())
	while t > 0:
		testCase()
		t -= 1
if __name__ == '__main__':
	main()	