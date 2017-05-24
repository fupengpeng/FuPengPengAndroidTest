package com.fupengpeng.sugarorm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.fupengpeng.sugarorm.bean.Article;
import com.orm.SugarRecord;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * sugar orm 用法说明
 *     1.model的build.grade添加依赖：
 *         compile 'com.github.satyan:sugar:1.5'
 *     2.自定义Application继承android.app.Application
 *         //在应用初始化的时候被调用
 *         onCreate方法中添加
 *             SugarContext.init(this);
 *         onTerminate方法中添加
 *             SugarContext.terminate();
 *     3.在Mainifest文件中
 *         <application android:name="自定义Application">
 *             <meta-data
 *                 android:name="DATABASE"
 *                 android:value="sugar_test.db" />
 *             <meta-data
 *                 android:name="VERSION"
 *                 android:value="1" />
 *             <meta-data
 *                  android:name="QUERY_LOG"
 *                 android:value="true" />
 *             <meta-data
 *                 android:name="DOMAIN_PACKAGE_NAME"
 *                 android:value="com.fupengpeng.sugarorm" />
 *         </application>
 *         四个meta-data分别确定了：
 *             01.创建的数据库db的文件名，将在/data/data/{你的应用包名}/databases下创建对应的文件
 *             02.数据库版本号
 *             03.是否允许SugarORM记录log
 *             04.创建数据库表对应的Bean所在的包的路径
 *
 *     6.具体用法详见代码中
 */
public class SugarORMActivity extends AppCompatActivity {
    /**
     * ButterKnife解绑对象
     */
    private Unbinder unbinder;

    /**
     * SugarActivity页面控件的获取
     */
    @BindView(R.id.tv_login_username01)
    TextView tvLoginUsername01;
    @BindView(R.id.tv_login_password01)
    TextView tvLoginPassword01;
    @BindView(R.id.tv_login_uid01)
    TextView tvLoginUid01;
    @BindView(R.id.tv_login_username02)
    TextView tvLoginUsername02;
    @BindView(R.id.tv_login_password02)
    TextView tvLoginPassword02;
    @BindView(R.id.tv_login_uid02)
    TextView tvLoginUid02;
    @BindView(R.id.tv_login_username03)
    TextView tvLoginUsername03;
    @BindView(R.id.tv_login_password03)
    TextView tvLoginPassword03;
    @BindView(R.id.tv_login_uid03)
    TextView tvLoginUid03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugar_orm);
        unbinder = ButterKnife.bind(this);

        /**
         * 添加数据
         */
        saveEntity();
        /**
         * 更新数据（查找数据，进行更换数据）
         */
        updateEntity();
        /**
         * 查找数据
         */
        loadEntity();
        /**
         * 删除数据
         */
        deleteEntity();

    }

    /**
     * 删除数据
     */
    private void deleteEntity() {
        Article article01 = Article.findById(Article.class,1);
        article01.delete();
    }

    /**
     * 更新数据（查找数据，进行更换数据）
     */
    private void updateEntity() {
        Article article01 = Article.findById(Article.class,1);
        tvLoginUsername01.setText(article01.getUsername());
        tvLoginPassword01.setText(article01.getPassword());
        tvLoginUid01.setText(article01.getUid());
        article01.setUsername("lisi");
        article01.setPassword("654321");
        article01.setUid("17791654654");
        article01.save();
    }

    /**
     * 查找数据
     */
    private void loadEntity() {
        Article article03 = Article.findById(Article.class,1);
        tvLoginUsername03.setText(article03.getUsername());
        tvLoginPassword03.setText(article03.getPassword());
        tvLoginUid03.setText(article03.getUid());
        Article article02 = Article.findById(Article.class,2);
        tvLoginUsername02.setText(article02.getUsername());
        tvLoginPassword02.setText(article02.getPassword());
        tvLoginUid02.setText(article02.getUid());
    }

    /**
     * 添加数据
     */
    private void saveEntity() {
        Article article01 = new Article("fupengpeng","123456","17791654327");
        article01.save();

        Article article02 = new Article("zhangsan","123457","17791654328");
        SugarRecord.save(article02);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除绑定
        unbinder.unbind();
    }
}
