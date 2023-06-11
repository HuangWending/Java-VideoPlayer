import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.media.*;
import java.net.URL;

public class VideoPlayer extends JFrame {
    private Player mediaPlayer;
    private Component videoComponent;
    private JButton playButton;
    private JButton pauseButton;
    private JSlider speedSlider;
    private JSlider volumeSlider;

    public VideoPlayer() {
        setTitle("视频播放器");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建功能按钮
        JPanel buttonPanel = new JPanel();
        playButton = new JButton("播放");
        pauseButton = new JButton("暂停");
        speedSlider = new JSlider(0, 200, 100);
        volumeSlider = new JSlider(0, 100, 50);

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pause();
            }
        });

        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                setPlaybackSpeed();
            }
        });

        volumeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                setVolume();
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(speedSlider);
        buttonPanel.add(volumeSlider);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void play() {
        if (mediaPlayer != null && mediaPlayer.getState() == Controller.Started) {
            return; // 如果正在播放，则不执行任何操作
        }

        // 弹出文件选择对话框
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // 创建媒体播放器
                URL mediaURL = selectedFile.toURI().toURL();
                mediaPlayer = Manager.createRealizedPlayer(mediaURL);
                videoComponent = mediaPlayer.getVisualComponent();

                if (videoComponent != null) {
                    add(videoComponent, BorderLayout.CENTER);
                }

                mediaPlayer.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.getState() == Controller.Started) {
            mediaPlayer.stop();
        }
    }

    private void setPlaybackSpeed() {
        if (mediaPlayer != null && mediaPlayer.getState() == Controller.Started) {
            float speed = speedSlider.getValue() / 100.0f;
            mediaPlayer.setRate(speed);
        }
    }

    private void setVolume() {
        if (mediaPlayer != null && mediaPlayer.getState() == Controller.Started) {
            float volume = volumeSlider.getValue() / 100.0f;
            Component control = mediaPlayer.getControlPanelComponent();
            if (control instanceof BasicControl) {
                ((BasicControl) control).setVolume(volume);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VideoPlayer player = new VideoPlayer();
                player.setVisible(true);
            }
        });
    }
}