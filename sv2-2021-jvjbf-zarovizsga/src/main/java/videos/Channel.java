package videos;

public class Channel {
    private String channel;
    private int subscription;
    private int number_of_videos;

    public Channel(String channel, int subscription, int number_of_videos) {
        this.channel = channel;
        this.subscription = subscription;
        this.number_of_videos = number_of_videos;
    }

    public String getChannelName() {
        return channel;
    }

    public int getSubscription() {
        return subscription;
    }

    public int getNumber_of_videos() {
        return number_of_videos;
    }
}
