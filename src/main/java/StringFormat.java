import java.util.Formatter;

class StringFormat {
	private StringBuilder sb;
	
	public StringFormat(){
		sb = new StringBuilder();
	}
	public StringFormat(String format, Object... args){
		sb = new StringBuilder();
		appendf(format,args);
	}
	public void format(String format, Object... args) {
		sb.append(new Formatter().format(format, args).toString());	
	}
	public StringFormat appendf(String format, Object... args) {
		sb.append(new Formatter().format(format, args).toString());	
		return this;
	}
	public StringFormat append(String str) {
		sb.append(str);
        return this;
    }
    @Override public String toString(){
		return sb.toString();
	}
	
	public void setLength(int i) {
		sb.setLength(i);
	}
	
	public int length() {
		return sb.length();
	}
	
	/**
	 * StringBuilder {@inheritDoc}
	 */
	public void replace(int i, int length, String s) {
		sb = sb.replace(i, length, s);
	}
}
