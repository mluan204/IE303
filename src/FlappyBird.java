import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image topPipImage;
    Image bottomPipImage;

    public class Bird {
        int x;
        int y;
        int width = 34;
        int height = 24;
        Image image;

        Bird(Image image) {
            this.image = image;
            resetSetting();
        }

        public void resetSetting() {
            x = boardWidth / 8;
            y = boardHeight / 8;
        }
    }

    public class Pipe {
        int x = boardWidth;
        int y = 0;
        int width = 64;
        int height = 512;
        Image image;
        boolean isTheBirdPass = false;

        Pipe(Image image) {
            this.image = image;
        }

    }

    Bird bird;
    ArrayList<Pipe> pipes = new ArrayList<Pipe>();

    int birdVelocity = 0;
    int pipeVelocity = -3;
    int gravity = 1;
    boolean isGameOver = false;
    double score = 0;

    Timer gameLoop;
    Timer placePipeLoop;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));

        setFocusable(true);

        addKeyListener(this);

        // loading images
        backgroundImage = new ImageIcon(getClass().getResource("/images/flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("/images/flappybird.png")).getImage();
        topPipImage = new ImageIcon(getClass().getResource("/images/toppipe.png")).getImage();
        bottomPipImage = new ImageIcon(getClass().getResource("/images/bottompipe.png")).getImage();

        bird = new Bird(birdImage);

        // 60 FPS
        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();

        placePipeLoop = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipeLoop.start();

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
        graphics.drawImage(bird.image, bird.x, bird.y, bird.width, bird.height, null);

        for (Pipe pipe : pipes) {
            graphics.drawImage(pipe.image, pipe.x, pipe.y, pipe.width, pipe.height,
                    null);
        }

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Arial", Font.BOLD, 22));

        if (!isGameOver) {
            graphics.drawString("Score: " + (int) score, 10, 30);
        } else {
            graphics.drawString("Game over!! Your score is: " + (int) score, 10, 30);
        }
    }

    public void moveBird() {
        birdVelocity += gravity;
        bird.y += birdVelocity;
        bird.y = Math.max(0, bird.y);
        // bird.y = Math.min(boardHeight - bird.height, bird.y);

        if (bird.y + bird.height > boardHeight) {
            isGameOver = true;
        }

        if (bird.y == 0) {
            isGameOver = true;
        }

    }

    public void movePipes() {
        for (Pipe pipe : pipes) {
            pipe.x += pipeVelocity;

            if (hasCollision(bird, pipe)) {
                isGameOver = true;
            }

            if (!pipe.isTheBirdPass && bird.x > pipe.x + pipe.width) {
                pipe.isTheBirdPass = true;
                score += 0.5;
            }
        }
    }

    public void placePipes() {
        Pipe topPipe = new Pipe(topPipImage);
        int randomYPipe = (int) (topPipe.y - (topPipe.height / 4) - Math.random() * (topPipe.height / 2));
        topPipe.y = randomYPipe;

        // Đặt vị trí x của trụ trên
        // if (pipes.isEmpty()) {
        // topPipe.x = boardWidth; // Trụ đầu tiên xuất hiện ở cạnh phải màn hình
        // } else {
        // topPipe.x = pipes.get(pipes.size() - 1).x + 300; // Tăng khoảng cách ngang
        // giữa các trụ
        // }
        pipes.add(topPipe);

        int spaceBetweenPipes = boardHeight / 4;

        Pipe bottomPipe = new Pipe(bottomPipImage);
        bottomPipe.y += topPipe.y + topPipe.height + spaceBetweenPipes;

        bottomPipe.x = topPipe.x;
        pipes.add(bottomPipe);
    }

    // Xử lý va chạm với cột
    // x chim < x cột + rộng cột
    // x chim + rộng chim > x cột
    // y chim < y cột + cao cột
    // y chim + cao chim > y cột
    public boolean hasCollision(Bird bird, Pipe pipe) {
        return bird.x < pipe.x + pipe.width &&
                bird.x + bird.width > pipe.x &&
                bird.y < pipe.y + pipe.height &&
                bird.y + bird.height > pipe.y;
    }

    public void resetSetting() {
        bird.resetSetting();
        pipes.clear();

        birdVelocity = 0;
        pipeVelocity = -4;
        gravity = 1;
        score = 0;
        isGameOver = false;

        gameLoop.start();
        placePipeLoop.start();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_SPACE) | (e.getKeyCode() == KeyEvent.VK_ENTER)) {
            birdVelocity = -10;
            if (isGameOver) {
                resetSetting();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveBird();
        movePipes();
        repaint();

        if (isGameOver) {
            placePipeLoop.stop();
            gameLoop.stop();
        }

    }
}
