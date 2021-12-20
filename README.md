## Описание проекта

Библиотека интеграции виджета с WebView для использования в приложениях партнеров и собственном приложении "Огонь".


## Установка зависимостей

Для установки зависимостей необходим [sdkmanager](https://developer.android.com/studio/command-line/sdkmanager) (является частью [Command line tools](https://developer.android.com/studio#downloads)).
Ручная установка зависимостей не требуется т.к. для этого используется Gradle Wrapper.

## Сборка

```bash
gradlew sdk:assemble
```

Артефакты сборки находятся в папке `./sdk/build/outputs/aar`

## JitPack

Библиотеку можно подключить к проекту с помощью [JitPack](https://jitpack.io).
Для этого необходимо подключить JitPack в корневом build.gradle:

```bash
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

И добавить библиотеку в зависимости:

```bash
dependencies {
    implementation 'com.github.ogon-ru:android-sdk:1.0.2'
}
```