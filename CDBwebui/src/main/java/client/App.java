package client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
//		new CliUI();
		List<String> t = new ArrayList<String>();
		t.add("1");
		t.add("2");
		String s = t.stream().collect(Collectors.joining("\",\""));
		System.out.println(s);
	}
}