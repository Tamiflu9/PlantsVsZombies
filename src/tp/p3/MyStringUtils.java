package tp.p3;

import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.nio.file.Files; 
import java.nio.file.InvalidPathException; 

public class MyStringUtils {
	public static String repeat(String elmnt, int length) {
		String result = ""; for (int i = 0; i < length; i++) {
			result += elmnt;
		} return result;
	}
	
	public static String centre(String text, int len){
		String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
		float mid = (out.length()/2);
		float start = mid-(len/2); 
		float end = start + len; 
		return out.substring((int)start, (int)end); 
	} 
	// returns true if string argument is a valid ﬁlename 
	public static boolean isValidFilename(String ﬁlename) { 
		try { 
			Paths.get(ﬁlename);
			return true; 
		} catch (InvalidPathException ipe) { 
			return false; 
			} 
		} 
	// returns true if ﬁle with that name exists (in which case, it may not be accessible ) 
	public static boolean ﬁleExists(String ﬁlename) { 
		try { 
			Path path = Paths.get(ﬁlename); 
			return Files.exists(path) && Files.isRegularFile(path);
		} catch (InvalidPathException ipe) {
			return false;
			// ﬁlename invalid => ﬁle cannot exist 
		} 
	} 
	// returns true if ﬁle wth that name exists and is readable 
	public static boolean isReadable(String ﬁlename) { 
		try { 
			Path path = Paths.get(ﬁlename); 
		return Files.exists(path) && Files.isRegularFile(path) && Files.isReadable(path); 
		} catch (InvalidPathException ipe) { 
			return false;
			// ﬁlename invalid => ﬁle cannot exist , never mind be readable 
		} 
	} 
		
}