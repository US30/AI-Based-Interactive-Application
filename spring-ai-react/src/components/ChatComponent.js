import React, { useState } from "react";

function ChatComponent() {
    const [prompt, setPrompt] = useState("");
    const [chatresponces, setChatResponses] = useState([]);

    const askAI = async () => {
        try {
            const response = await fetch(`https://localhost:8080/ask-ai?promt=${prompt}`)
            const data = await response.text();
            console.log(data);
            setChatResponses(data);
        } catch (error) {
            console.error("Error generating response", error);
        }
    };
    return (
        <div>
            <h1>Talk to AI</h1>
            <input type="text" placeholder="Enter your message" value={prompt} onChange={(e) => setPrompt(e.target.value)}/>
            <button onClick={askAI}>Ask AI</button>
            <div className="output">
                <p>{chatresponces}</p>
            </div>
        </div>
    );
}


export default ChatComponent;