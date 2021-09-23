## Описание проекта

Библиотека интеграции виджета с WebView для использования в приложениях партнеров и собственном приложении "Огонь".


## Установка зависимостей

Для установки зависимостей необходим [sdkmanager](https://developer.android.com/studio/command-line/sdkmanager) (является частью [Command line tools](https://developer.android.com/studio#downloads).
Ручная установка зависимостей не требуется т.к. для этого используется Gradle Wrapper.

## Сборка

```bash
gradlew sdk:assemble
```

Артефакты сборки находятся в папке `./sdk/build/outputs/aar`