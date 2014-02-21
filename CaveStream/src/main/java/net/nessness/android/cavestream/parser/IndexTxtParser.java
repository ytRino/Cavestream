package net.nessness.android.cavestream.parser;

import android.util.Log;

import net.nessness.android.cavestream.model.Channel;
import net.nessness.android.cavestream.model.IndexTxtChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndexTxtParser extends ChannelParser {

    private static final String TAG = IndexTxtParser.class.getSimpleName();

    @Override
    public List<Channel> parse(String txt) throws IllegalStateException {
        return parse(new Scanner(txt));
    }

    protected List<Channel> parse(Scanner scanner) throws IllegalStateException {
        List<Channel> list = new ArrayList<Channel>();
        while (scanner.hasNextLine()) {
            Channel ch = parseLine(scanner.nextLine());
            list.add(ch);
        }
        return list;
    }

    private static Channel parseLine(String line) {
        String[] data = line.split("<>");
        Log.v(TAG, line + " @ " + data.length);
        for (int i = 0, dataLength = data.length; i < dataLength; i++) {
            String s = data[i];
            Log.v(TAG, i + ": " + s);
        }

        IndexTxtChannel ch = new IndexTxtChannel();
        ch.setName(data[0]);
        ch.setId(data[1]);
        ch.setIp(data[2]);
        ch.setUrl(data[3]);
        ch.setGenre(data[4]);
        ch.setDescription(data[5]);
        ch.setListeners(Integer.parseInt(data[6]));
        ch.setKbps(Integer.parseInt(data[8]));
        ch.setTime(data[15]);
        ch.setComment(data[17]);
        return ch;
    }

    // index.txt <- cavetube
    // ジャンル <- ジャンル
    // 詳細 <- タイトル
    // コメント <- 詳細
    //http://www23.atwiki.jp/pecapiracy/pages/14.html
    //(1)<>(2)<>(3)<>(4)<>(5)<>(6)<>(7)<>(8)<>(9)<>(10)<>(11)<>(12)<>(13)<>(14)<>(15)<>(16)<>(17)<>(18)<>(19)
    //(1) 配信チャンネル名
    //(2) チャンネルID
    //(3) 配信者のIPアドレス:PeerCast使用ポート
    //(4) コンタクトURL
    //(5) ジャンル (*注1)
    //(6) 詳細 (*注1)
    //(7) 視聴者数(隠蔽する設定にしている場合は -1 になる) (*注2)
    //(8) リレー数(隠蔽する設定にしている場合は -1 になる) (*注2)
    //(9) ビットレート(kbps)
    //(10) ファイル形式
    //(11)
    //(12)
    //(13)
    //(14)
    //(15) 配信チャンネル名がURLエンコードされたもの
    //(16) 配信時間 (*注2)
    //(17) ステータス(通常は"click")
    //(18) 配信者からのコメント (*注1)
    //(19)

}
