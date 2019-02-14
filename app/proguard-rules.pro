#-------------------------------------------定制化区域----------------------------------------------
#---------------------------------1.实体类---------------------------------
-keep class zmz.zhao.com.zmz.bean.** { *; }


#-------------------------------------------------------------------------

#---------------------------------2.第三方包-------------------------------

# ==================友盟统计=================
-keep class com.umeng.** {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep public class com.bw.movie.R$*{
public static final int *;
}

# ==================环信混淆start=================
    -keep class com.hyphenate.** {*;}
    -dontwarn  com.hyphenate.**
    # ==================环信end======================

    # ==================bugly start==================
    -dontwarn com.tencent.bugly.**
    -keep public interface com.tencent.**
    -keep public class com.tencent.** {*;}
    -keep public class com.tencent.bugly.**{*;}
    # ==================bugly end====================

    # ===============百度定位 start====================
    -keep class vi.com.gdi.** { *; }
    -keep public class com.baidu.** {*;}
    -keep public class com.mobclick.** {*;}
    -dontwarn com.baidu.mapapi.utils.*
    -dontwarn com.baidu.platform.comapi.b.*
    -dontwarn com.baidu.platform.comapi.map.*
    # ===============百度定位 end======================


    #================greendao==========================
    -keep class org.greenrobot.greendao.**{*;}
    -keep public interface org.greenrobot.greendao.**
    -keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
    }
    -keep class **$Properties
    -keep class net.sqlcipher.database.**{*;}
    -keep public interface net.sqlcipher.database.**
    -dontwarn net.sqlcipher.database.**
    -dontwarn org.greenrobot.greendao.**

    #==============微信支付=================
    -keep class com.tencent.** { *;}
    -keep class com.tencent.wxop.** { *; }


    #eventBus
    -keepattributes *Annotation*
    -keepclassmembers class ** {
        @org.greenrobot.eventbus.Subscribe <methods>;
    }
    -keep enum org.greenrobot.eventbus.ThreadMode { *; }
    -keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
        <init>(java.lang.Throwable);
    }

    #glide
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }


    -dontwarn javax.annotation.**
    -dontwarn javax.inject.**
    # OkHttp3
    -dontwarn okhttp3.logging.**
    -keep class okhttp3.internal.**{*;}
    -dontwarn okio.**
    # Retrofit
    -dontwarn retrofit2.**
    -keep class retrofit2.** { *; }
    -dontnote retrofit2.Platform
    -dontnote retrofit2.Platform$IOS$MainThreadExecutor
    -dontwarn retrofit2.Platform$Java8
    -keepattributes Signature
    -keepattributes Exceptions

    # RxJava RxAndroid
    -dontwarn sun.misc.**
    -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
        long producerIndex;
        long consumerIndex;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode producerNode;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
        rx.internal.util.atomic.LinkedQueueNode consumerNode;
    }

    # Gson
    -keep class com.google.gson.stream.** { *; }
    -keepattributes EnclosingMethod



#-------------------------------------------------------------------------

#---------------------------------3.与js互相调用的类------------------------



#-------------------------------------------------------------------------

#---------------------------------4.反射相关的类和方法-----------------------



#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------

#-------------------------------------------基本不用动区域--------------------------------------------
#---------------------------------基本指令区----------------------------------
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------