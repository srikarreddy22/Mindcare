# HealthCare: Intelligent Student Wellness Platform

MindCare is a comprehensive, AI-driven wellness and emergency-response web application designed for students. The platform leverages machine learning for predictive wellness routines, natural language processing for real-time conversational analysis, and integrated telecommunications mapping to support holistic tracking and urgent interventions.

## 🚀 Features
- **Conversational Chatbot**: Real-time distress-detecting chat interface leveraging the Google Generative AI (Gemini) API.
- **Predictive Wellness Assessment**: Conducts multi-variate machine-learning inference via Scikit-Learn pipelines to curate custom, dynamically-generated wellbeing regimes.
- **Automated Emergency Dispatch System**: Seamless telecommunication escalations orchestrated utilizing Twilio SDK capabilities for direct SMS and SMS automated alert generation.
- **Microservices System Architecture**: Discrete scaling implemented mapping Node UI proxies interfacing to a Spring Boot backend and an isolated Python computation node. 

## 🏗️ Technology Stack

**Frontend**
- React & Vite 
- TypeScript 
- Tailwind CSS (Applying Glassmorphism Aesthetic Principles)

**Backend 1 (Core Routing & APIs)**
- Java 21 & Spring Boot
- Twilio Telecommunication SDK
- Firebase Admin User Management API
- Google Generative AI REST Services

**Backend 2 (Machine Learning Service)**
- Python & FastAPI 
- Scikit-Learn & Pandas (Model Inference & DataFrame pipeline construction)

## 📁 System Architecture
- `src/` - React Application containing UI component abstractions.
- `backend-spring/` - Core Java App orchestrating the primary Chat/Emergency routing.
- `backend/` - The Python computational unit evaluating predictive statistics on port `8000`.

## ⚙️ Prerequisites
Ensure that your development environment includes:
- **Node.js** v20+
- **JDK** v21+
- **Python** 3.9+ 
- A valid `Twilio` Account & Verified Sender Number.
- Google Gemini API Keys.

## 🔑 Environment Variables
Create a `.env` file mapped at the root directories of both the **Frontend** and **Spring Boot Backend**:
```env
VITE_FIREBASE_API_KEY=xxxxx
TWILIO_SID=xxxxx
TWILIO_AUTH=xxxxx
TWILIO_NUMBER=xxxxx
GEMINI_API_KEY=xxxxx
```

## 🛠️ Running Locally

1. **Start the Frontend Web Application**:
```bash
npm install
npm run dev
# The React layer initiates on http://localhost:5173
```

2. **Start the Core Spring Boot APIs**:
```bash
cd backend-spring
./mvnw clean package spring-boot:run
# Mounts the backend APIs onto http://localhost:8080 
```

3. **Start the Machine Learning Service**:
```bash
cd backend
pip install -r requirements.txt # (or fastApi, uvicorn, pandas, scikit-learn manually)
python -m uvicorn main:app --reload --port 8000
# Commences the survey predictor pipeline
```

## 📜 Notice
This project prioritizes confidential wellness implementations. Please ensure `.env` file structures are strictly maintained out of open-source repository pushes.
