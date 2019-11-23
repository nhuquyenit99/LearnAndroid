package com.cndd.mydictionary.ui.favorite;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cndd.mydictionary.CustomAdapter;
import com.cndd.mydictionary.DetailsActivity;
import com.cndd.mydictionary.MainActivity;
import com.cndd.mydictionary.R;
import com.cndd.mydictionary.Word;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private FavoriteViewModel favoriteViewModel;
    private ListView lvFavorite;
    private CustomAdapter<Word> favAdapter;
    private List<Word> favorites;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                ViewModelProviders.of(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);

        favorites = MainActivity.favoriteDatabase.getAllWords();

        lvFavorite = root.findViewById(R.id.lv_favorites);
        favAdapter = new CustomAdapter<>(getActivity(), R.layout.row_listview, favorites);
        lvFavorite.setAdapter(favAdapter);

        lvFavorite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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