package com.enixyu.cookbook;

import android.app.Application;
import com.enixyu.cookbook.repo.CookbookSqliteHelper;
import com.enixyu.cookbook.repo.RecipeRepo;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class CookbookApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    CookbookSqliteHelper dbHelper = new CookbookSqliteHelper(this);
    RecipeRepo.INSTANCE.initialize(dbHelper);
  }
}
