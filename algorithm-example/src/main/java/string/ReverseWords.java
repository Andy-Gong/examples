package string;

public class ReverseWords {

    public String reverseWords(String s) {
        String[] strings = s.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            char[] chars = strings[i].toCharArray();
            for (int k = 0, j = chars.length-1; k < j; k++, j--) {
                char tmp = chars[k];
                chars[k] = chars[j];
                chars[j] = tmp;
            }
            if (i == strings.length - 1) {
                stringBuilder.append(chars);
            } else {
                stringBuilder.append(chars).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}
