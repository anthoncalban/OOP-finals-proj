import javax.swing.*;
import java.awt.event.*;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;

public class ResumeBuilder {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Resume Builder");
        frame.setSize(500, 900);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Personal Info
        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(130, 20, 300, 25);
        frame.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 60, 100, 25);
        frame.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(130, 60, 300, 25);
        frame.add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(20, 100, 100, 25);
        frame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(130, 100, 300, 25);
        frame.add(phoneField);

        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 140, 100, 25);
        frame.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(130, 140, 300, 25);
        frame.add(addressField);

        JLabel educationLabel = new JLabel("Education:");
        educationLabel.setBounds(20, 180, 100, 25);
        frame.add(educationLabel);

        JTextArea educationArea = new JTextArea();
        JScrollPane educationScroll = new JScrollPane(educationArea);
        educationScroll.setBounds(130, 180, 300, 80);
        frame.add(educationScroll);

        JLabel experienceLabel = new JLabel("Experience:");
        experienceLabel.setBounds(20, 270, 100, 25);
        frame.add(experienceLabel);

        JTextArea experienceArea = new JTextArea();
        JScrollPane experienceScroll = new JScrollPane(experienceArea);
        experienceScroll.setBounds(130, 270, 300, 80);
        frame.add(experienceScroll);

        JLabel skillsLabel = new JLabel("Skills:");
        skillsLabel.setBounds(20, 360, 100, 25);
        frame.add(skillsLabel);

        JTextArea skillsArea = new JTextArea();
        JScrollPane skillsScroll = new JScrollPane(skillsArea);
        skillsScroll.setBounds(130, 360, 300, 80);
        frame.add(skillsScroll);

        JLabel awardsLabel = new JLabel("Awards and Honors:");
        awardsLabel.setBounds(20, 450, 150, 25);
        frame.add(awardsLabel);

        JTextField awardsField = new JTextField();
        awardsField.setBounds(130, 450, 300, 25);
        frame.add(awardsField);

        JLabel referencesLabel = new JLabel("References:");
        referencesLabel.setBounds(20, 490, 100, 25);
        frame.add(referencesLabel);

        JTextField referencesField = new JTextField();
        referencesField.setBounds(130, 490, 300, 25);
        frame.add(referencesField);

        JTextArea resumeArea = new JTextArea();
        resumeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resumeArea);
        scrollPane.setBounds(20, 530, 440, 150);
        frame.add(scrollPane);

        JButton generateButton = new JButton("Generate Resume");
        generateButton.setBounds(150, 700, 180, 30);  // Adjusted the position of the button
        frame.add(generateButton);

        JButton exportButton = new JButton("Export as PDF");
        exportButton.setBounds(150, 740, 180, 30);  // Adjusted the position of the button
        frame.add(exportButton);

        generateButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String awards = awardsField.getText();
            String references = referencesField.getText();
            String education = educationArea.getText();
            String experience = experienceArea.getText();
            String skills = skillsArea.getText();

            StringBuilder resume = new StringBuilder();
            resume.append("===== RESUME =====\n\n");
            resume.append("Name: ").append(name).append("\n");
            resume.append("Email: ").append(email).append("\n");
            resume.append("Phone: ").append(phone).append("\n");
            resume.append("Address: ").append(address).append("\n");
            resume.append("Education:\n").append(education).append("\n");
            resume.append("Experience:\n").append(experience).append("\n");
            resume.append("Skills:\n").append(skills).append("\n");
            resume.append("Awards and Honors: ").append(awards).append("\n");
            resume.append("References: ").append(references).append("\n");

            resumeArea.setText(resume.toString());
        });

        exportButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Resume as PDF");
            int result = fileChooser.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    Document document = new Document();
                    PdfWriter.getInstance(document, new FileOutputStream(filePath));
                    document.open();

                    Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.DARK_GRAY);  // Dark gray color for title
                    Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLUE);     // Blue color for labels
                    Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);         // Black for normal text
                    Font referencesFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);  // Clear and bold for references section

                    Paragraph nameTitle = new Paragraph(nameField.getText(), titleFont);
                    nameTitle.setAlignment(Element.ALIGN_CENTER);  
                    document.add(nameTitle);
                    document.add(new Paragraph("\n"));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Email:", labelFont));
                    document.add(new Paragraph(emailField.getText(), textFont));
                    document.add(new Paragraph("Phone:", labelFont));
                    document.add(new Paragraph(phoneField.getText(), textFont));
                    document.add(new Paragraph("Address:", labelFont));
                    document.add(new Paragraph(addressField.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Education:", labelFont));
                    document.add(new Paragraph(educationArea.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Experience:", labelFont));
                    document.add(new Paragraph(experienceArea.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Skills:", labelFont));
                    document.add(new Paragraph(skillsArea.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("Awards and Honors:", labelFont));
                    document.add(new Paragraph(awardsField.getText(), textFont));
                    document.add(new Paragraph("\n"));

                    document.add(new Paragraph("References:", referencesFont));
                    document.add(new Paragraph(referencesField.getText(), referencesFont));

                    document.close();
                    JOptionPane.showMessageDialog(frame, "PDF saved successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Failed to save PDF: " + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }
}
