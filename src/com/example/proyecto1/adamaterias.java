package com.example.proyecto1;

import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class adamaterias extends BaseAdapter implements  OnClickListener {
	private Context context;
	private List<materias> materias;
	
	public adamaterias(Context context, List<materias> materias) {
		
		this.context = context;
		this.materias = materias;
	}
	@Override
	public int getCount() {
		return materias.size();
	}

	@Override
	public Object getItem(int position) {
		return materias.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		materias entry = materias.get(position);
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowinicial, null);
		}
        TextView tvContact = (TextView) convertView.findViewById(R.id.textView1);
        tvContact.setText(entry.getNombre());

        TextView tvPhone = (TextView) convertView.findViewById(R.id.textView3);
        tvPhone.setText(entry.getCreditos()+"");     
        
        TextView tvPhone2 = (TextView) convertView.findViewById(R.id.textView4);
        if(entry.getNota()!=-1.0){
        	tvPhone2.setText(entry.getNota()+"");
        }else {
        	tvPhone2.setText("");
        }
        
        Button btnRemove = (Button) convertView.findViewById(R.id.button1);
        btnRemove.setFocusableInTouchMode(false);
        btnRemove.setFocusable(false);
        btnRemove.setOnClickListener(this);
        btnRemove.setTag(entry);
          
        
        return convertView;
          
	}
	@Override
	public void onClick(View view) {
	     materias entry = (materias) view.getTag();
	     materias.remove(entry);
	     notifyDataSetChanged();
	     helper helper= OpenHelperManager.getHelper(context,helper.class);
		 RuntimeExceptionDao<materias, String> materiasDao = helper.getMateriaRuntimeDao();
		 materiasDao.delete(entry);
	     

	}

	
	

}
