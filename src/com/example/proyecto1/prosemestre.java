package com.example.proyecto1;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class prosemestre extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.deseadosemestre, container, false);
		ListView lv = (ListView) rootView.findViewById(R.id.listView1);
		double creditos = getArguments().getDouble("creditos");
		double deseada = getArguments().getDouble("deseada");
		double actual = getArguments().getDouble("actual");
		helper helper= OpenHelperManager.getHelper(rootView.getContext(),helper.class);
		RuntimeExceptionDao<materias, String> materiasDao = helper.getMateriaRuntimeDao();
		List<materias> materias = materiasDao.queryForAll();
		double cre = 0;
		double nocre = 0;
		double snocre = 0;
		for (materias notas : materias) {
			if(notas.getNota()==-1.0){
				cre+=notas.getCreditos();
			}else{
				nocre+=notas.getCreditos();
				snocre+=notas.getCreditos()*notas.getNota();
			}
		}
		double de = ((deseada*(cre+nocre+creditos))-snocre-actual*creditos)/cre;
		List<String> esta = new ArrayList<String>();
		for (materias notas : materias) {
			if(notas.getNota()==-1.0){
				notas.setNota(de);
				esta.add(notas.getNombre()+" - "+notas.getNota());
			}else{
				esta.add(notas.getNombre()+" - "+notas.getNota()+"*");
			}
			
		}
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                rootView.getContext(),
                android.R.layout.simple_list_item_1, esta );
		lv.setAdapter(arrayAdapter);
		
		Button btt = (Button) rootView.findViewById(R.id.button1);
		btt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});
		return rootView;
	}
}
