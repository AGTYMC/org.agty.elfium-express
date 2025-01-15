class AgtyFullView {
    static fullview = new AgtyFullView();

    static fullView() {
        return this.fullview;
    }

    static init(ids) {
        let $this = this;

        $(document).ajaxComplete(function () {
            $this.initElements(ids);
        });

        $this.initElements(ids);
    }

    static initElements(ids) {
        $(ids).on("click", function (ui) {
            let uri = $(this).attr('href');
            if (uri) {
                AgtyFullView.fullView().open({
                    content: '<img src="' + uri + '" style="max-height: 100%; max-width: 100%;" />'
                });
            }
            return false;
        });
    }

    createFullview() {
        this.destroy();

        $('body').append(
            '<div data-agty-fullview-id="agty-fullview" class="agty-full-view agty-full-view-nodisplay" data-agty-fullview-id="agty-full-view">'
            +  '<div class="agty-fullview-close-button" data-agty-fullview-id="agty-fullview-close-button">X</div>'
            +  '<div data-agty-fullview-id="agty-fullview-content" class="agty-full-view-block">'
            +  '</div>'
            + '</div>'
        );
    }

    initButtons() {
        $('div[data-agty-fullview-id="agty-fullview-close-button"]').on("click", this.destroy);
    }

    destroy() {
        $('div[data-agty-fullview-id="agty-fullview"]').remove();
    }

    open(data) {
        this.createFullview();

        if (data) {
            if (data.content) this.setContent(data.content);
        }

        this.show();
    }

    show() {
        let windowHeight = document.documentElement.clientHeight;
        $('div[data-agty-fullview-id="agty-fullview-content"]').css("height", windowHeight - 20);
        $('div[data-agty-fullview-id="agty-fullview"]').removeClass('agty-full-view-nodisplay');
        this.initButtons();
    }

    setContent(title) {
        $('div[data-agty-fullview-id="agty-fullview-content"]').html(title);
    }
}