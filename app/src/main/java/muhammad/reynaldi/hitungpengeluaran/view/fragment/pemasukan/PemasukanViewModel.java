package muhammad.reynaldi.hitungpengeluaran.view.fragment.pemasukan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import muhammad.reynaldi.hitungpengeluaran.database.DatabaseClient;
import muhammad.reynaldi.hitungpengeluaran.database.dao.DatabaseDao;
import muhammad.reynaldi.hitungpengeluaran.model.ModelDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PemasukanViewModel extends AndroidViewModel {

    private LiveData<List<ModelDatabase>> mPemasukan;
    private DatabaseDao databaseDao;
    private LiveData<Integer> mTotalPrice;

    public PemasukanViewModel(@NonNull Application application) {
        super(application);

        databaseDao = DatabaseClient.getInstance(application).getAppDatabase().databaseDao();
        mPemasukan = databaseDao.getAllPemasukan();
        mTotalPrice = databaseDao.getTotalPemasukan();
    }

    public LiveData<List<ModelDatabase>> getPemasukan() {
        return mPemasukan;
    }

    public LiveData<Integer> getTotalPemasukan() {
        return mTotalPrice;
    }

    public void deleteAllData() {
        Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        databaseDao.deleteAllPemasukan();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public String deleteSingleData(final int uid) {
        String sKeterangan;
        try {
            Completable.fromAction(() -> databaseDao.deleteSinglePemasukan(uid))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
            sKeterangan = "OK";
        } catch (Exception e) {
            sKeterangan = "NO";
        }
        return sKeterangan;
    }

}
