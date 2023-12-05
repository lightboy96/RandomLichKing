import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.rmi.UnexpectedException;

public class Main {
    private static javax.swing.JTextPane jConsole;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton KillButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelConsole;

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {

            PlayerManager test = new PlayerManager(10);
            test.playPlayerSound(1);

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */

        /*


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException ex) {
            java.util.logging.Logger.getLogger(RandomLichKingV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RandomLichKingV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RandomLichKingV2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        // Create and display the form

        java.awt.EventQueue.invokeLater(() ->
        {
            new RandomLichKingV2().setVisible(true);
        });

        try {

            Sounds s1 = new Sounds("data/Welcome.wav");
            Sounds s2 = new Sounds("data/FrostmourneHungers.wav");
            Desktop.getDesktop().open(new File("data/LichKingAnimatedWallpaper.mp4"));

            Timestamp welcomeTS = new Timestamp(System.currentTimeMillis());
            RandomLichKingV2.appendToPane("[" + RandomLichKingV2.sdf.format(welcomeTS) + "] " + "The Lich King says: I suppose a welcome is in order... So welcome, insects, welcome to MY WORLD! \n\n", Color.CYAN);

            s1.playClip();
            //RandomLichKingV2.playSound("data/Welcome.wav");
            Thread.sleep(15000); */
            /*
            RandomLichKingV2.setStarting(false);

            Thread newYearThread;
            newYearThread = new Thread(() ->
            {
                try {
                    Date date = RandomLichKingV2.newYearDateFormatter.parse("2021-12-31 23:59:40");
                    // Date date = newYearDateFormatter.parse("2021-12-25 18:59:40");
                    Timer timer = new Timer();
                    timer.schedule(new RandomLichKingV2.NewYearTask(), date);
                } catch (ParseException ex) {
                    Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            newYearThread.start();

            while (!RandomLichKingV2.getExiting()) {
                if (!RandomLichKingV2.getNewYear()) {
                    Timestamp dormantTS = new Timestamp(System.currentTimeMillis());
                    RandomLichKingV2.appendToPane("[" + RandomLichKingV2.sdf.format(dormantTS) + "] " + "The Lich King is dormant... \n\n", Color.WHITE);
                }

                int random = 0;
                random = new Random().nextInt((RandomLichKingV2.getMaxWait() - RandomLichKingV2.getMinWait()) + 1) + RandomLichKingV2.getMinWait();
                Thread.sleep(random);

                if (!RandomLichKingV2.getExiting() && !RandomLichKingV2.getNewYear()) {
                    Timestamp yellTS = new Timestamp(System.currentTimeMillis());
                    RandomLichKingV2.appendToPane("[" + RandomLichKingV2.sdf.format(yellTS) + "] " + "The Lich King yells: Frostmourne hungers! \n", Color.red);
                    RandomLichKingV2.appendToPane("\n", Color.red);
                    s2.playClip();
                    /*RandomLichKingV2.playSound("data/FrostmourneHungers.wav");
                    Thread.sleep(7000);*/
    }
}
        /*} catch (Exception ex) {
            Logger.getLogger(RandomLichKingV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
// End of variables declaration//GEN-END:variables

