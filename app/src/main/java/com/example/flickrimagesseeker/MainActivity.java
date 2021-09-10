package com.example.flickrimagesseeker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.flickrimagesseeker.databinding.ActivityItemDetailBinding;
import com.example.flickrimagesseeker.databinding.MainActivityBinding;
import com.example.flickrimagesseeker.utils.DialogUtils;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding mDataBinding;
    private NavController mNavController;
    private Consumer<Throwable> errorHandler = throwable -> {
        Timber.e(throwable);
        DialogUtils.showOneButtonDialog(this, R.string.unexpected_error, null);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxJavaPlugins.setErrorHandler(this.errorHandler);

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        mDataBinding.setLifecycleOwner(this);

        navigationSetUp();

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    private void navigationSetUp() {
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.
                Builder(mNavController.getGraph())
                .build();

        NavigationUI.setupActionBarWithNavController(this, mNavController, appBarConfiguration);
    }
}