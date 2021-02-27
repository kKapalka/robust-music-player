package pl.rethagos.musicplayer.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pl.rethagos.musicplayer.R;
import pl.rethagos.musicplayer.model.FolderPath;

/**
 * RecyclerViewAdapter used to display data regarding user folders containing audio files
 */
public class FolderPathAdapter extends RecyclerView.Adapter {

    ArrayList<FolderPath> folderPaths;
    RecyclerView recyclerView;
    OnFolderPathClickListener listener;

    public FolderPathAdapter(ArrayList<FolderPath> folderPathArrayList, RecyclerView recyclerView, OnFolderPathClickListener listener) {
        this.folderPaths = folderPathArrayList;
        this.recyclerView = recyclerView;
        this.listener = listener;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_folder_path, parent, false);
        view.setOnClickListener(v -> {
            int currentPosition = recyclerView.getChildAdapterPosition(v);
            listener.onClick(folderPaths.get(currentPosition));
        });
        return new FolderPathViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FolderPath folderPath = folderPaths.get(position);
        ((FolderPathViewHolder) holder).setUp(folderPath);
    }

    @Override
    public int getItemCount() {
        return folderPaths.size();
    }

    private class FolderPathViewHolder extends RecyclerView.ViewHolder{

        private TextView folderNameTextView;
        private TextView folderPathTextView;
        private Button folderPathNavigationButton;
        private FolderPath folderPath;

        public FolderPathViewHolder(@NonNull View itemView) {
            super(itemView);
            folderNameTextView = itemView.findViewById(R.id.folder_name_textview);
            folderPathTextView = itemView.findViewById(R.id.folder_path_textview);
            folderPathNavigationButton = itemView.findViewById(R.id.layout_folder_navi_button);
        }

        private void setUp(FolderPath folderPath) {
            this.folderPath = folderPath;
            folderNameTextView.setText(folderPath.getFolderName());
            folderPathTextView.setText(folderPath.getFolderPath());
        }
    }
}
