package cn.qiang129.sqlite_encryption;

import static com.j256.ormlite.android.AndroidConnectionSource.DB_PASSWORD;

import static cn.qiang129.sqlite_encryption.DatabaseHelper.DB_NAME;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

import java.sql.SQLException;
import java.util.List;

import cn.qiang129.sqlite_encryption.databinding.FragmentFirstBinding;
import cn.qiang129.sqlite_encryption.util.FileUtil;
import cn.qiang129.sqlite_encryption.util.ThreadPoolUtil;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResult();
                //NavHostFragment.findNavController(FirstFragment.this)
                //        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getResult() {
        DatabaseHelper dbHelper = DatabaseHelper.getHelper(getContext());

        // 查询数据库
        try {
            Dao dao = dbHelper.getDao(Table_SQLITE_ENCRYPTION.class);
            List<Table_SQLITE_ENCRYPTION> data = dao.queryForAll();
            for (Table_SQLITE_ENCRYPTION item : data) {
                Logger.d("XSFW: " + item);
            }
            binding.textviewFirst.setText(String.valueOf(data.size()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String path = DatabaseHelper.getHelper(getContext()).getReadableDatabase(DB_PASSWORD).getPath();
        binding.textviewSecond.setText(path);

        // 复制数据库到
        ThreadPoolUtil.getPoolProxy().execute(() -> {
            try {
                String dstPath = ContextCompat.getExternalFilesDirs(getContext(), null)[0].getAbsolutePath();
                String targetPath = dstPath + "/" + DB_NAME;
                FileUtil.copy(path, targetPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}