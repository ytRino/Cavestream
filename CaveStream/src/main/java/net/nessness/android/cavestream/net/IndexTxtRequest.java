package net.nessness.android.cavestream.net;

import net.nessness.android.cavestream.Const;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

public class IndexTxtRequest extends StringRequest {

    public IndexTxtRequest(Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.GET, Const.INDEX, listener, errorListener);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            // force utf-8
            parsed = new String(response.data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
