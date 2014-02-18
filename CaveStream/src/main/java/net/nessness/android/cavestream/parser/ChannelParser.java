package net.nessness.android.cavestream.parser;

import net.nessness.android.cavestream.model.Channel;

import java.util.List;

public abstract class ChannelParser {

    public abstract List<Channel> parse(String txt);

}
