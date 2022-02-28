package sentences;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        int last = sentence.length() - 1;
        String endChars = ".!?";
        String[] parts = sentence.split(" ");
        if (sentence.substring(0, 1).equals(sentence.substring(0, 1).toLowerCase())) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (!endChars.contains(sentence.substring(last))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
        if (parts.length >= 5) {
            return String.join(" ", parts[0], "...", parts[parts.length - 1]);
        }
        return sentence;
    }
}
