package com.github.iquee.music.track;

import com.github.iquee.music.utils.PopFromQueue;

public interface TrackService extends PopFromQueue<Track> {

	String create(Track track);

	Track get(String id);

	void receiveMessageFromQueue(Track track);
}
