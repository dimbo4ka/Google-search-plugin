import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Search extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        String selectedText = null;

        if (editor != null) {
            selectedText = editor.getSelectionModel().getSelectedText();
        }

        if (editor == null || selectedText == null || selectedText.isEmpty()) {
            selectedText = "python memes for true boys";
        }

        String encodedUrl;
        try {
            encodedUrl = URLEncoder.encode(selectedText, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException error) {
            Messages.showErrorDialog("Url encoding error: " + error.getMessage(), "error");
            return;
        }

        String url = String.format("https://www.google.com/search?q=%s", encodedUrl);
        Messages.showMessageDialog("Search in google:\n" + url, "Googling", Messages.getInformationIcon());

        BrowserUtil.browse(url);
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }
}
