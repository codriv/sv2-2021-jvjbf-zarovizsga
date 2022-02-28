package videos;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class VideoPlatform {

    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void readDataFromFile(Path path) {
        try(Scanner scanner = new Scanner(path)) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                addChannel(line);
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot open file for read!");
        }
    }

    private void addChannel(String line) {
        String[] parts = line.split(";");
        String channelName = parts[0];
        int subscriptions = Integer.parseInt(parts[1]);
        int numberOfVideos = Integer.parseInt(parts[2]);
        channels.add(new Channel(channelName, subscriptions, numberOfVideos));
    }

    public int calculateSumOfVideos() {
        return channels.stream().flatMapToInt(c -> IntStream.of(c.getNumberOfVideos())).sum();
    }
}
