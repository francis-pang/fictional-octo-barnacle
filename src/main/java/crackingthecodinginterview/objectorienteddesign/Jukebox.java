package crackingthecodinginterview.objectorienteddesign;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Jukebox {
  private int numberOfCoinsInserted;
  private Map<Integer, Song> songList;
  private Queue<Song> playList;

  public Jukebox(Song[] songList) {
    this.songList = new HashMap<>();
    for (Song song : songList) {
      this.songList.put(song.id, song);
    }
    playList = new ArrayDeque<>();
    numberOfCoinsInserted = 0;
  }

  public void insertCoin() {
    numberOfCoinsInserted++;
  }

  public void chooseASong() {
    showAvailableSongs();
    Song chosenSong = askToChooseSong();
    addSongToPlayList(chosenSong);
  }

  private void addSongToPlayList(Song chosenSong) {
    boolean successfulAddSong = playList.offer(chosenSong);
    final int WAITING_TIME;
    while (!successfulAddSong) {
      successfulAddSong = playList.offer(chosenSong);
    }
  }

  private Song askToChooseSong() {
    System.out.println("Please choose a song from the song list: ");
    Scanner input = new Scanner(System.in);
    // Assume that user choose by ID
    int chosenId = input.nextInt();
    if (!songList.containsKey(chosenId)) {
      System.out.println("You have chosen an invalid ID. Choose again.");
      askToChooseSong();
    }
    return songList.get(chosenId);
  }

  private void showAvailableSongs() {
    // Display all song in songList
  }

  class Song {
    int id;
    String title;
    Duration length;
    String artist;
  }
}
