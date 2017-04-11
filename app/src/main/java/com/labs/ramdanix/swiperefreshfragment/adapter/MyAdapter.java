package com.labs.ramdanix.swiperefreshfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.labs.ramdanix.swiperefreshfragment.MainActivity;
import com.labs.ramdanix.swiperefreshfragment.R;
import com.labs.ramdanix.swiperefreshfragment.fragment.MainFragment;
import com.labs.ramdanix.swiperefreshfragment.model.UserPinjam;

import java.util.List;

/**
 * Created by ramdanix on 11/04/17.
 */

public class MyAdapter extends BaseAdapter{

    //MainActivity mainActivity;
    MainFragment mainFragment;

    Context context;
    LayoutInflater inflater;
    List<UserPinjam> userPinjamsList;

    public MyAdapter(Context context, List<UserPinjam> userPinjamsList) {
        this.context = context;
        this.userPinjamsList = userPinjamsList;
    }

    @Override
    public int getCount() {
        return userPinjamsList.size();
    }

    @Override
    public Object getItem(int i) {
        return userPinjamsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public void addBarangToList(@NonNull List<UserPinjam> userPinjams)
    {

        if (userPinjamsList != null) {
            //mBookmark.clear();
            //Collections.reverse(list);

            userPinjamsList.addAll(userPinjams);
            //final int positionStart = mBookmark.size() + 1;

            //notifyItemRangeInserted(0, mBookmark.size()-1);

        } else {
            userPinjamsList = userPinjams;
        }

        notifyDataSetChanged();

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.custom_user_pinjam, null);

        TextView namaSiswa = (TextView) view.findViewById(R.id.tv_user_nama_pinjam);
        TextView kelas = (TextView) view.findViewById(R.id.tv_user_kelas_pinjam);
        TextView waktuPinjam = (TextView) view.findViewById(R.id.tv_waktu_pinjam);

        namaSiswa.setText(String.valueOf(userPinjamsList.get(i).getNama()));
        kelas.setText(String.valueOf(userPinjamsList.get(i).getKelas()));
        waktuPinjam.setText(String.valueOf(userPinjamsList.get(i).getWaktu_pinjam()));


        return view;
    }
}
