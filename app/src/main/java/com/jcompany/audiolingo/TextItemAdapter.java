package com.jcompany.audiolingo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class TextItemAdapter extends BaseAdapter {
    private Context context;
    private List<TextItem> textItemList;
    private LayoutInflater inflater;

    public TextItemAdapter(Context context, List<TextItem> textItemList){
        this.context = context;
        this.textItemList = textItemList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return textItemList.size();
    }

    @Override
    public TextItem getItem(int position) {
        return textItemList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.adapter_item, null);
        TextItem currentItem = getItem(i);
        String itemname = currentItem.getName();
        String itemtext = currentItem.getText();
        TextView itemNameView = view.findViewById(R.id.item_name);
        itemNameView.setText(itemname);
        Button item_button = view.findViewById(R.id.item_button);
        item_button.setText(itemtext);
        item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WorkActivity.class);
                // ajoutez des données supplémentaires à l'intention si nécessaire
                intent.putExtra("key",itemtext);
                context.startActivity(intent);
            }
        });

        return view;
    }
}
