package xmlvalidator;

import static java.lang.System.*;
import java.io.*;
import java.util.regex.*;

public class BasicXmlValidator implements XmlValidator {

	@Override
	public String[] validate(String xmlDocument) {
		// TODO Auto-generated method stub
		BasicStringStack stackHolder = new BasicStringStack();
		BasicStringStack numHolder = new BasicStringStack();
		String expr = "<[^<>]+>";
		Pattern p = Pattern.compile(expr);

		String text = xmlDocument;

		Matcher m = p.matcher(text);

		BasicStringStack lineNum = new BasicStringStack();
		int num, j = 0;
		String b = Integer.toString(text.indexOf("\n"));
		if (Integer.parseInt(b) == -1) {
			lineNum.push(Integer.toString(text.length()));
		} else {
			lineNum.push(b);
			while (true) {
				num = text.indexOf("\n", Integer.parseInt(b) + 1);

				if (num == -1) {
					break;
				}

				lineNum.push(Integer.toString(num));
				b = Integer.toString(num);
				j++;
			}
		}

		while (m.find()) {
			// System.out.println(m.group());
			String value = m.group();
			String[] arrValue = value.split("\\s+", 2);
			value = arrValue[0];
			StringBuffer tempz = new StringBuffer(value);
			if (value.charAt(value.length() - 1) != '>')
				tempz = tempz.append('>');
			value = tempz.toString();
			// if (arrValue.length >= 2) {
			// if (arrValue[1].contains("/") == true)
			// continue;
			// } else {
			if (value.charAt(1) != '?' && value.charAt(1) != '!') {
				if (value.charAt(1) != '/') {
					// inserting beginning tag
					stackHolder.push(value);
					numHolder.push(Integer.toString(m.start()));
				} else {
					if (stackHolder.getCount() == 0) {

						// orphan reason
						int k = j;
						while (true) {
							if (lineNum.peek(k) == null) {
								k = -1;
								break;
							}
							if (m.start() < Integer.parseInt(lineNum.peek(k))) {
								break;
							}
							k--;
						}

						String[] reason = new String[3];
						reason[0] = "Orphan closing tag";
						reason[1] = m.group().substring(2, m.group().length() - 1);// .substring(1, m.group().length() -
																					// 1);
						reason[2] = Integer.toString(j - k + 1);
						return reason;
					} else {
						String temp = m.group().substring(2, m.group().length() - 1);
						String temp2 = stackHolder.pop();
						temp2 = temp2.substring(1, temp2.length() - 1);
						String temp3 = numHolder.pop();

						if (temp.equals(temp2) == false) {

							String[] reason = new String[5];
							reason[0] = "Tag mismatch";
							reason[1] = temp2;
							int k = j;

							while (true) {
								if (Integer.parseInt(temp3) < Integer.parseInt(lineNum.peek(k))) {
									break;
								}
								k--;
							}
							reason[2] = Integer.toString(j - k + 1);
							reason[3] = temp;
							k = j;
							while (true) {
								if (m.start() < Integer.parseInt(lineNum.peek(k))) {
									break;
								}
								k--;
							}
							reason[4] = Integer.toString(j - k + 1);

							return reason;
						} // end if
							// }
					}
				}
			}

		}
		if (stackHolder.getCount() > 0)

		{

			int k = j;
			String pos = numHolder.pop();
			int z = Integer.parseInt(pos);
			while (true) {
				if (lineNum.peek(k) == null) {
					k = -1;
					break;
				}
				if (z < Integer.parseInt(lineNum.peek(k))) {
					break;
				}
				k--;
			}

			String[] reason = new String[3];
			reason[0] = "Unclosed tag at end";
			String temp = stackHolder.pop();
			reason[1] = temp.substring(1, temp.length() - 1);
			reason[2] = Integer.toString(j - k + 1);
			return reason;

		}
		return null;

	}

}
