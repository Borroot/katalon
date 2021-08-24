![](misc/showcase.gif)

# Katalon
A computerversion of the strategy game Katalon/Quint-X implemented using JavaFX. The rules for this game can be found [here](https://www.w4kangoeroe.nl/kangoeroe/files/9713/7604/2930/katalon_special.pdf) (in Dutch).

The Javadoc documentation for this program can be found [here](https://borroot.github.io/katalon/ "Github Pages Documentation").

## Execution instructions
First download the JavaFX SDK from [here](https://gluonhq.com/products/javafx/).
```
$ PATH_TO_FX=$(pwd)/javafx-sdk-11.0.2/lib/
$ cd src
$ javac --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml game/Main.java
$ java  --module-path $PATH_TO_FX --add-modules javafx.controls,javafx.fxml --class-path ../resources:. game.Main
```