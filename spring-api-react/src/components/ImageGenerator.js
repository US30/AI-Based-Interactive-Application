import React, { useState } from "react";

function ImageGenerator() {
    const [promt, setPromt] = useState("");
    const [imageUrls, setImageUrls] = useState([]);

    const generateImage = async () => {
        try {
            const response = await fetch(`https://localhost:8080/generate-image?promt=${promt}`)
            const urls = await response.json();
            console.log(urls);
            setImageUrls(urls);
        } catch (error) {
            console.error("Error generating image:", error);
        }
    };
    return (
        <div className="tab-content">
            <h1>Generate Image</h1>
            <input
                type="text"
                placeholder="Enter a promt for image generation"
                value={promt}
                onChange={(e) => setPromt(e.target.value)}
            />
            <button onClick={generateImage}>Generate Image</button>
            <div className="image-grid">
                {imageUrls.map((url, index) => (
                    <img key={index} src={url} alt={`Generated Image ${index}`} />
                ))}
                {[...Array(4-imageUrls.length)].map((_, index) => (
                    <div key={index + imageUrls.length} className="empty-image-slot"></div>
                ))}
            </div>
        </div>
        
    );
}

export default ImageGenerator;