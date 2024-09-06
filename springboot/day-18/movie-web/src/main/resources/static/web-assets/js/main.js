document.addEventListener('DOMContentLoaded', function () {
    // Lấy phần tử modal
    const modalTrailer = document.getElementById('modal-trailer');

    // Sử dụng sự kiện Bootstrap để phát hiện khi modal bị đóng
    modalTrailer.addEventListener('hidden.bs.modal', function () {
        // Lấy iframe
        const iframe = document.getElementById('video-trailer');
        // Lưu URL của video
        const videoSrc = iframe.src;
        // Tạm thời xóa src để dừng video
        iframe.src = '';
        // Đặt lại src khi modal đóng
        iframe.src = videoSrc;
    });
});