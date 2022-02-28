package videos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class VideoPlatform {
    private List<Channel> channels = new ArrayList<>();

    public List<Channel> getChannels() {
        return channels;
    }

    public void readDataFromFile(Path path){
        try(BufferedReader bf = Files.newBufferedReader(path)){
            bf.readLine();
            String line;
            while((line=bf.readLine())!=null){
                String[] temp = line.split(";");
                channels.add(new Channel(temp[0],Integer.parseInt(temp[1]),Integer.parseInt(temp[2])));
            }
        }catch (IOException ioe){
            throw new IllegalArgumentException("Cannot open file for read!", ioe);
        }
    }

//    private Channel parseLine(String line){
//        String[] temp = line.split(";");
//        String channels = temp[0];
//        int subs = Integer.parseInt(temp[1]);
//        int numOfVId = Integer.parseInt(temp[2]);
//        Channel channel = new Channel(channels,subs,numOfVId);
//        return channel;
//    }

    public int calculateSumOfVideos(){
        return channels.stream()
                .mapToInt(Channel::getNumber_of_videos)
                .sum();

    }
}
