class AgtyDialog {
    static dialog;

    static init() {
        if (this.dialog == null) {
            this.dialog = new AgtyDialog();
        }
        this.destroy();
        this.createDialog();
        this.initButtons();
    }

    static createDialog() {
        $('body').append(
            '<div class="agty-dialog agty-dialog-nodisplay" data-agty-dialog-id="agty-dialog">'
            +    '<div class="agty-dialog-block">'
            +        '<div class="agty-dialog-head">'
            +            '<div class="agty-dialog-head-buttons">'
            +               '<div class="agty-dialog-head-buttons-button agty-dialog-head-buttons-close" data-agty-dialog-id="close-button">'
            +                    'X'
            +                '</div>'
            +            '</div>'
            +            '<div class="agty-dialog-head-title" data-agty-dialog-id="head-title"></div>'
            +        '</div>'
            +        '<div class="agty-dialog-content" data-agty-dialog-id="dialog-content"></div>'
            +        '<div class="agty-dialog-status" data-agty-dialog-id="dialog-status"></div>'
            +    '</div>'
            + '</div>'
        );
    }

    static initButtons() {
        $('div[data-agty-dialog-id="close-button"]').on("click", this.close);
    }

    static close() {
        $('div[data-agty-dialog-id="agty-dialog"]').addClass('agty-dialog-nodisplay');
        AgtyDialog.clear();
    }

    static destroy() {
        $('div[data-agty-dialog-id="agty-dialog"]').remove();
    }

    static open(data) {
        this.init();

        if (data) {
            if (data.title) this.dialog.setTitle(data.title);
            if (data.content) this.dialog.setContent(data.content);
            if (data.status) this.dialog.setStatus(data.status);
        }

        this.dialog.show();
    }

    static openIfDataSet(data) {
        if (data) {
            this.open(data);
        }
    }

    static clear() {
        if (this.dialog) {
            this.dialog.setTitle('');
            this.dialog.setStatus('');
            this.dialog.setContent('');
        }
    }

    show() {
        $('div[data-agty-dialog-id="agty-dialog"]').removeClass('agty-dialog-nodisplay');
    }

    setTitle(title) {
        $('div[data-agty-dialog-id="head-title"]').text(title);
    }

    setStatus(title) {
        $('div[data-agty-dialog-id="dialog-status"]').text(title);
    }

    setContent(title) {
        $('div[data-agty-dialog-id="dialog-content"]').html(title);
    }
}