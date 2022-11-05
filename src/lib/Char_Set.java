package lib;

public class Char_Set {
	char character;
	int frequency;
	String bitcode;


	public Char_Set(char c, int f) {

		this.character = c;
		this.frequency = f;
		this.bitcode = null;

	}

	public char getCharacter() {
		return this.character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public int getFrequency() {
		return this.frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	

	public String getBitcode() {
		return bitcode;
	}

	public void setBitcode(String bitcode) {
		this.bitcode = bitcode;
	}

	@Override
	public String toString() {
		return "Char_Set [character=" + character + ", frequency=" + frequency + ", bitcode=" + bitcode + "]";
	}

	

	
	
	
}
