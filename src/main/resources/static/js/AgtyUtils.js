class AgtyUtils {
    /**
     * Скрыть блок
     */
    static hideBlock(data) {
        $(data.id).addClass('nodisplay');
        if (data.remove) {
            $(data.remove).remove();
        }
    }

    /**
     * Показать блок
     */
    static showBlock(data) {
        $(data.id).removeClass('nodisplay');
    }

    /**
     * Показать скрыть блок
     * @var data object
     */
    static showHide(data) {
        if ($(data.id).is(":visible")) {
            $(data.id).hide(data.hideTime ? data.hideTime : 200);
        } else {
            $(data.id).show(data.showTime ? data.showTime : 500);
        }
    }

    static echoJson(text) {
        this.echo(JSON.stringify(text, null, " "));
    }

    static echo(string) {
        console.log(string);
    }

    static ajaxJsonError(jqXHR, exception) {
        let msg = '';
        if (jqXHR.status === 0) {
            msg = 'Not connect.\n Verify Network.';
        } else if (jqXHR.status === 404) {
            msg = 'Requested page not found. [404]';
        } else if (jqXHR.status === 500) {
            msg = 'Internal Server Error [500].';
        } else if (exception === 'parsererror') {
            msg = 'Requested JSON parse failed.';
        } else if (exception === 'timeout') {
            msg = 'Time out error.';
        } else if (exception === 'abort') {
            msg = 'Ajax request aborted.';
        } else {
            msg = 'Uncaught Error.\n';
        }
        this.echo(msg + jqXHR.responseText);
    }
}