package sentences;

public class SentenceTransformerWtihRegex {

    public String shortenSentence(String sentence) {
        if (!sentence.matches("^[A-Z].*")) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
        if (!sentence.matches(".*[.!?]$")) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
        String[] parts = sentence.split(" ");
        String lastPart = parts[parts.length - 1];
        if (parts.length >= 5) {
            return String.join(" ", parts[0], "...", lastPart);
        }
        return sentence;
    }
}
