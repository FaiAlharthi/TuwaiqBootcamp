<div align="center">

<img src="assets/packup_logo.png" alt="PackUp logo" width="110" />

# PackUp

**Store more. Stress less.**

Peer-to-peer storage space rental platform — Java Spring Boot backend.

</div>

---

## Overview

PackUp connects people who have extra space (garages, storage rooms, closets, etc.) with people who need somewhere to store their belongings. Space owners list their available spots, renters book them, and both sides can leave reviews after the booking is complete.

The backend is a REST API built with **Spring Boot**, backed by **MySQL**, with two extra integrations that make the experience smoother:

- 📱 **WhatsApp notifications** (via GREEN-API) — users get notified automatically about booking updates.
- 🤖 **AI-generated space descriptions** (via Google Gemini) — owners can auto-generate a clean, bilingual description for their listing instead of writing one from scratch.

## Integrations

| Purpose | Technology |
|---|---|
| Notifications | GREEN-API (WhatsApp) |
| AI | Google Gemini API |

## Core Features

- **User accounts** — registration and login by phone number.
- **Space listings** — create, browse, update, and delete storage spaces, with AI-assisted description generation.
- **Bookings** — request, accept/reject, track status, and manage the full booking lifecycle between renter and owner.
- **Reviews** — renters can rate and review a space after their booking.
- **WhatsApp alerts** — key booking events (new request, confirmation, etc.) trigger an automatic WhatsApp message to the relevant user.

## API Overview

The API is organized into four main resources:

| Controller | Base Path | What it handles |
|---|---|---|
| `UserController` | `/api/v1/user` | Registration, login, and user profile management |
| `SpaceController` | `/api/v1/space` | Listing, searching, and managing storage spaces (incl. AI description generation) |
| `BookingController` | `/api/v1/booking` | Creating and managing booking requests between renters and owners |
| `ReviewController` | `/api/v1/review` | Posting and retrieving reviews for a space |

Each controller has the standard CRUD set (`get all`, `create`, `update`, `delete`), plus the business-logic endpoints below.

### Additional Endpoints

**User** (`/api/v1/user`)

| Method | Endpoint | Description |
|---|---|---|
| PUT | `/blockRenter/{ownerId}/{blockedRenter}` | Owner blocks a renter from booking their spaces |
| GET | `/getBlockedRenters/{ownerId}` | List the renters an owner has blocked |

**Space** (`/api/v1/space`)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/ownerSpaces/{ownerId}` | List all spaces owned by a user |
| GET | `/citySpace/{city}` | Search available spaces by city |
| GET | `/budgetSpace/{budget}` | Search available spaces at or under a budget |
| PUT | `/changeStatus/{spaceId}/{ownerId}` | Toggle a space between AVAILABLE / UNAVAILABLE |
| POST | `/generateDescription` | AI-generate a bilingual listing description (Gemini) |

**Booking** (`/api/v1/booking`)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/renterBookings/{renterId}` | List all bookings made by a renter |
| GET | `/getSpaceBookings/{spaceId}` | List all bookings for a specific space |
| GET | `/checkAvailability/{spaceId}/{startDate}/{endDate}` | Check if a space is free for a date range |
| PUT | `/cancelBooking/{bookingId}/{renterId}` | Renter cancels a booking before it starts |
| PUT | `/extendBooking/{bookingId}/{renterId}/{newEndDate}` | Renter extends a booking's end date |
| PUT | `/completeBooking/{bookingId}/{renterId}/{newAmount}` | Settle final payment and mark a booking complete |
| PUT | `/updateBookingStatuses` | Batch-move bookings from BOOKED → ONGOING once their start date arrives |
| GET | `/calculateProfits/{ownerId}` | Total profits earned by an owner across all their spaces |
| GET | `/loyaltyDiscountUsers/{ownerId}` | Renters eligible for a loyalty discount with this owner |

**Review** (`/api/v1/review`)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/getAvgRating/{id}` | Average rating for a user (as reviewee) |

A full, detailed breakdown of every endpoint — including request/response shapes and every possible "bad request" case with its exact message — is available in [`PackUp_API_Endpoint_Reference.pdf`](./PackUp_API_Endpoint_Reference.pdf) (also provided as an editable `.docx`).

## Project Structure (high level)

```
src/main/java/.../
 ├── controller/     # REST controllers (User, Space, Booking, Review)
 ├── service/        # Business logic
 ├── repository/     # Spring Data JPA repositories
 ├── model/          # Entities
 ├── dto/            # Request/response DTOs
 └── util/           # WhatsApp (GREEN-API) & Gemini AI integration helpers
```

## Notes

- Authentication is phone-number based; there is no third-party OAuth login.
- WhatsApp messages are sent through GREEN-API, so a configured GREEN-API instance ID/token is required to enable that feature.
- AI descriptions are generated through the Google Gemini API, so a Gemini API key is required to enable that feature.

## Author

**Fai Alharthi** — capstone project.
