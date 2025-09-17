export async function uploadImage(file, presignedUrl) {
    if (!file) throw new Error("파일이 필요합니다.");
    if (!presignedUrl) throw new Error("잘못된 URL입니다.");

    // 1. 이미지 디코딩
    const imgBitmap = await createImageBitmap(file);

    // 2. OffscreenCanvas 생성 (브라우저 전용)
    const canvas = new OffscreenCanvas(imgBitmap.width, imgBitmap.height);
    const ctx = canvas.getContext("2d");
    ctx.drawImage(imgBitmap, 0, 0);

    // 3. JPG Blob으로 변환 (품질 0.85)
    const jpgBlob = await canvas.convertToBlob({
        type: "image/jpeg",
        quality: 0.85,
    });

    // 4. Presigned URL로 업로드
    const res = await fetch(presignedUrl, {
        method: "PUT",
        body: jpgBlob,
        headers: {
            "Content-Type": "image/jpeg",
        },
    });

    return res.ok;
}