# Java-VideoPlayer
Java视频播放器
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.media.*;
import java.net.URL;

这些是导入所需的Java类和库。

public class VideoPlayer extends JFrame {
    //...
}

定义了名为`VideoPlayer`的类，继承自`JFrame`，表示视频播放器的主窗口。

private Player mediaPlayer;
private Component videoComponent;
private JButton playButton;
private JButton pauseButton;
private JSlider speedSlider;
private JSlider volumeSlider;

声明了视频播放器的成员变量，包括`Player mediaPlayer`（用于播放视频的播放器实例）、`Component videoComponent`（用于显示视频的组件实例）、`JButton playButton`（播放按钮）、`JButton pauseButton`（暂停按钮）、`JSlider speedSlider`（用于调整播放速度的滑块）和`JSlider volumeSlider`（用于调整音量的滑块）。

public VideoPlayer() {
    //...
}

构造函数`VideoPlayer()`用于初始化视频播放器的窗口和功能按钮。

setTitle("视频播放器");
setSize(800, 600);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

设置视频播放器窗口的标题、大小和关闭操作。

JPanel buttonPanel = new JPanel();
playButton = new JButton("播放");
pauseButton = new JButton("暂停");
speedSlider = new JSlider(0, 200, 100);
volumeSlider = new JSlider(0, 100, 50);
创建一个面板`buttonPanel`，用于容纳功能按钮。然后创建播放、暂停、速度和音量控制按钮，并初始化滑块的初始值。

playButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        play();
    }
});
为播放按钮添加点击事件的监听器，当按钮被点击时调用`play()`方法。

pauseButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        pause();
    }
});
为暂停按钮添加点击事件的监听器，当按钮被点击时调用`pause()`方法。

speedSlider.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
        setPlaybackSpeed();
    }
});
为速度滑块添加值变化事件的监听器，当滑块的值发生变化时调用`setPlaybackSpeed()`方法。

volumeSlider.addChangeListener(new ChangeListener() {
    public void stateChanged(ChangeEvent e) {
        setVolume();
    }
});
为音量滑块添加值变化事件的监听器，当滑块的值发生变化时调用`setVolume()`方法。

buttonPanel.add(playButton);
buttonPanel.add(pauseButton);
buttonPanel.add(speedSlider);
buttonPanel.add(volumeSlider);

将功能按钮和滑块添加到按钮面板中。

add(buttonPanel, BorderLayout.SOUTH);
将按钮面板添加到视频播放器

窗口的南部（底部）。

private void play() {
    //...
}
`play()`方法用于处理播放按钮的点击事件，包括选择视频文件、创建媒体播放器并开始播放。

private void pause() {
    //...
}
`pause()`方法用于处理暂停按钮的点击事件，暂停正在播放的视频。

private void setPlaybackSpeed() {
    //...
}
`setPlaybackSpeed()`方法用于处理速度滑块的滑动事件，设置视频的播放速度。

private void setVolume() {
    //...
}
`setVolume()`方法用于处理音量滑块的滑动事件，设置视频的音量。

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            VideoPlayer player = new VideoPlayer();
            player.setVisible(true);
        }
    });
}
`main()`方法是程序的入口点，通过调用`SwingUtilities.invokeLater()`方法，在事件调度线程中创建并显示视频播放器窗口。
