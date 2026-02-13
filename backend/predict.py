import joblib
import numpy as np

# Load trained model
model = joblib.load("../models/rul_model.pkl")

def predict_rul(input_data):
    input_array = np.array(input_data).reshape(1, -1)
    prediction = model.predict(input_array)
    return float(prediction[0])


if __name__ == "__main__":
    sample_input = [0.0] * 24
    result = predict_rul(sample_input)
    print("Predicted RUL:", result)
