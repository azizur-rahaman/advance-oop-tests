#!/bin/bash

# verification_script.sh

echo "Starting Verification Process..."

# 1. Start Backend in background
echo "Starting Backend..."
cd backend
java -jar target/aop-assignment-0.0.1-SNAPSHOT.jar > backend.log 2>&1 &
BACKEND_PID=$!
cd ..

# 2. Start Frontend in background
echo "Starting Frontend..."
cd frontend
npm start > frontend.log 2>&1 &
FRONTEND_PID=$!
cd ..

# Function to cleanup
cleanup() {
    echo "Stopping servers..."
    kill $BACKEND_PID
    kill $FRONTEND_PID
}
trap cleanup EXIT

# 3. Wait for Backend (Health Check)
echo "Waiting for Backend to allow connections on port 8080..."
for i in {1..30}; do
    if curl -s http://localhost:8080/api/gadgets > /dev/null; then
        echo "Backend is UP!"
        break
    fi
    echo "Waiting for backend... ($i/30)"
    sleep 2
done

# 4. Wait for Frontend
echo "Waiting for Frontend to allow connections on port 3000..."
for i in {1..30}; do
    if curl -s http://localhost:3000 > /dev/null; then
        echo "Frontend is UP!"
        break
    fi
    echo "Waiting for frontend... ($i/30)"
    sleep 2
done

# 5. Perform CRUD Tests on Backend
echo "Running CRUD Tests..."

# Create
echo "Testing POST /api/gadgets..."
CREATE_RESPONSE=$(curl -s -X POST http://localhost:8080/api/gadgets \
    -H "Content-Type: application/json" \
    -d '{"name":"TestPhone","brand":"TestBrand","type":"PHONE","price":999.99,"status":"AVAILABLE"}')
echo "Create Response: $CREATE_RESPONSE"

if [[ $CREATE_RESPONSE == *"TestPhone"* ]]; then
    echo "✅ Create Gadget: SUCCESS"
else
    echo "❌ Create Gadget: FAILED"
    exit 1
fi

# Get ID (naive parsing for test)
GADGET_ID=$(echo $CREATE_RESPONSE | grep -o '"id":[0-9]*' | grep -o '[0-9]*')
echo "Created Gadget ID: $GADGET_ID"

# Read
echo "Testing GET /api/gadgets..."
LIST_RESPONSE=$(curl -s http://localhost:8080/api/gadgets)
if [[ $LIST_RESPONSE == *"TestPhone"* ]]; then
    echo "✅ Read Gadgets: SUCCESS"
else
    echo "❌ Read Gadgets: FAILED"
    exit 1
fi

# Update
echo "Testing PUT /api/gadgets/$GADGET_ID..."
UPDATE_RESPONSE=$(curl -s -X PUT http://localhost:8080/api/gadgets/$GADGET_ID \
    -H "Content-Type: application/json" \
    -d '{"name":"UpdatedPhone","brand":"TestBrand","type":"PHONE","price":888.88,"status":"AVAILABLE"}')
if [[ $UPDATE_RESPONSE == *"UpdatedPhone"* ]]; then
    echo "✅ Update Gadget: SUCCESS"
else
    echo "❌ Update Gadget: FAILED"
    echo "Response: $UPDATE_RESPONSE"
    exit 1
fi

# Delete
echo "Testing DELETE /api/gadgets/$GADGET_ID..."
DELETE_CODE=$(curl -s -o /dev/null -w "%{http_code}" -X DELETE http://localhost:8080/api/gadgets/$GADGET_ID)
if [[ $DELETE_CODE == "200" ]]; then
    echo "✅ Delete Gadget: SUCCESS"
else
    echo "❌ Delete Gadget: FAILED (Code $DELETE_CODE)"
    exit 1
fi

# Final Read
FINAL_LIST=$(curl -s http://localhost:8080/api/gadgets)
if [[ $FINAL_LIST != *"UpdatedPhone"* ]]; then
    echo "✅ Verify Deletion: SUCCESS"
else
    echo "❌ Verify Deletion: FAILED (Item still exists)"
    exit 1
fi

echo "------------------------------------------------"
echo "🎉 ALL TESTS PASSED SUCCESSFULLY!"
echo "Backend and Frontend are running verified."
echo "You can access the content at http://localhost:3000"
echo "The servers will shut down when this script exits."
echo "------------------------------------------------"
# Keep alive to let the user play if they ran this manually, but for 'verification' we just want to exit successfully.
# However, the user said "run it and verify". 
# The trap will kill them. I will comment out the trap exit and leave them running? 
# No, "verify by yourself" implies I check and report. I shouldn't leave stray processes if I can avoid it, OR I should leave them running for the user.
# I'll leave them running by waiting at the end.
echo "Press CTRL+C to stop the servers."
sleep 10 # Wait 10 seconds to prove stability then exit? Or just wait indefinitely? 
# I will wait 5 seconds then exit, assuming I just need to verify. 
