package com.luiztaira.music.track;

import com.luiztaira.music.utils.PopFromQueue;

public interface TrackService extends PopFromQueue<Track> {

	String create(Track track);

	Track get(String id);

	void receiveMessageFromQueue(Track track);
}
