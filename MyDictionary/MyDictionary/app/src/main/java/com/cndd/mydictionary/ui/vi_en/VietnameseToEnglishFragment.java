package com.cndd.mydictionary.ui.vi_en;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cndd.mydictionary.CreateWordActivity;
import com.cndd.mydictionary.CustomAdapter;
import com.cndd.mydictionary.DetailsActivity;
import com.cndd.mydictionary.MainActivity;
import com.cndd.mydictionary.R;
import com.cndd.mydictionary.Word;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VietnameseToEnglishFragment extends Fragment {

    private VietnameseToEnglishViewModel vietnameseToEnglishViewModel;
    private CustomAdapter<String> lvCustomAdapter;
    private CustomAdapter<Word> autoTvCustomAdapter;
    private AutoCompleteTextView search;
    private ListView lvWords;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vietnameseToEnglishViewModel =
                ViewModelProviders.of(this).get(VietnameseToEnglishViewModel.class);
        View root = inflater.inflate(R.layout.fragment_vi_en, container, false);
        FloatingActionButton fab = root.findViewById(R.id.create_vi_en);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CreateWordActivity.class);
                startActivity(intent);
            }
        });

        lvWords = (ListView) root.findViewById(R.id.lv_words_vi_en);
        lvCustomAdapter = new CustomAdapter<>(getActivity(), R.layout.row_listview, MainActivity.vi_en);
        lvWords.setAdapter(lvCustomAdapter);

        //Set click on list view
        lvWords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Word word = (Word) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("word", word);
                bundle.putSerializable("kind", "vi_en");
                intent.putExtra("package", bundle);

                startActivity(intent);
            }
        });

        search = (AutoCompleteTextView) root.findViewById(R.id.search_vi_en);


        //Auto complete
        autoTvCustomAdapter = new CustomAdapter<>(getActivity(), R.layout.row_listview,MainActivity.vi_en);
        search.setThreshold(1);
        search.setAdapter(autoTvCustomAdapter);

        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = (Word) adapterView.getItemAtPosition(position);

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("word", word);
                intent.putExtra("package", bundle);

                startActivity(intent);
            }
        });
        return root;
    }
}