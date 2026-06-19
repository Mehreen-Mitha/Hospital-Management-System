# Hospital Management System (HMS)

A modular, multi-tier desktop administration system engineered in Java. This application pairs **Java Swing** graphical view layers with a persistent, decoupled **Flat-File Database architecture (Java File I/O Streams)**. The system implements real-time state synchronization, business logic validation, transactional logging, and historical log-based state reconstruction across several clinical modules.

---

## 🔍 System Architecture & Design Topology

The system is organized around a **central switchboard topology**. The repository decouples graphical presentation layers, structural business logic models, and persistent transaction streams using a clean Model-View-Controller (MVC) style alignment.

┌──────────────────────┐
              │ FrontAccessPage (UI) │
              └──────────┬───────────┘
                         │
                         ▼
                ┌──────────────────┐
                │  LoginPage (UI)  │
                └────────┬─────────┘
                         │
                         ▼
                ┌──────────────────┐
                │  SecondPage (UI) │◀─────────────────────────┐
                └────────┬─────────┘                          │
                         │ (Matrix Navigation Routes)         │
     ┌───────────────────┼───────────────────┐                │
     ▼                   ▼                   ▼                │
┌────────────────┐  ┌────────────────┐  ┌────────────────┐        │
│PatientPage (UI)│  │PharmacyPage(UI)│  │RevenuePage (UI)│        │
└────────┬───────┘  └────────┬───────┘  └────────┬───────┘        │
│                   │                   │                │
▼                   ▼                   ▼                │
┌────────────────┐  ┌────────────────┐  ┌────────────────┐        │
│Sub-Decks/Models│  │ Pharmacy.java  │  │MonthlyRev.java │        │
│(Patient, etc.) │  │    (Model)     │  │    (Model)     │        │
└────────┬───────┘  └────────┬───────┘  └────────┬───────┘        │
│                   │                   │                │
▼ (File Stream IO)  ▼ (Append-Only Log) ▼ (Report IO)    │
┌────────────────┐  ┌────────────────┐  ┌────────────────┐        │
│  patients.txt  │  │  pharmacy.txt  │  │  revenue.txt   │        │
└────────────────┘  └────────────────┘  └────────────────┘        │
│                   │                   │                │
└───────────────────┴───────────────────┴────────────────┘

### Architectural Safeguards:
* **Context Switching Memory Optimization:** To minimize runtime overhead, navigation nodes trigger immediate frame resource flushes (`frame.dispose()`) before initializing child dashboards, maintaining a lightweight single-window memory profile.
* **Component Abstraction Factories:** Rather than manually restyling repetitive UI assets, visual widgets are processed via reusable internal helper methods (`createTile`, `styleButton`, `createLabel`). This centralizes theme definitions (e.g., Maroon `#5B0E0E` styling matrices) and enforces layout consistency across independent pages.

---

## 🛠️ Core Subsystem Matrix

### 1. Unified Demographics & Identity Registry
Establishes the foundational human-resource domain mapping for the system.
* **Abstract Parent Design:** Utilizes an abstract root class (`Person`) to implement property grouping (IDs, basic information, contact vectors), guaranteeing that generic base objects cannot be directly instantiated.
* **Polymorphic Contracts:** Dictates a standardized tracking method configuration by declaring an abstract routine pipeline (`displayInfo()`), forcing downstream child subclasses (`Doctor`, `Nurse`, `Patient`) to define custom local output logic.

### 2. Equipment Inventory & Asset Logistics
Handles facility supply pipelines, clinical drug formularies, and medical equipment distributions.
* **Data Encapsulation Walls:** Domain states (stock counts, unit margins, and costs) are strictly isolated via `private` access modifiers to prevent unauthorized state mutations from external controller hooks.
* **Defensive Guard Clauses:** Intercepts numeric updates using inline boundary conditions, ensuring negative restock amounts or stock over-allocations are caught and rejected before modifying fields.

### 3. Financial Auditing & Revenue Tracking
Gathers operational transaction entries across multiple care units to evaluate institutional health.
* **Multi-Channel Compilation:** Aggregates real-time intake balances from patient invoicing tables, pharmacy points-of-sale, and miscellaneous service options into a singular evaluation model.
* **Dual Reporting Channels:** Features synchronized processing loops that instantly output transactional audits down to local graphical monitoring panels (`JTextArea` streams) while writing permanent logs to long-term storage records.

---

## 💻 Engineering Competencies & CS Theory

### Object-Oriented Design (OOD)
* **Inheritance & Super Delegation:** Maps real-world organizational layers cleanly into software design frameworks. Subclasses extend parent constructs (`extends Person`) and route baseline indexing keys back up to root abstractions via structured `super()` delegation hooks.
* **Defensive State Inits:** No-argument constructor forms initialize primitive properties with strict fallback data values (`0`, `"NULL"`), systematically neutralizing potential `NullPointerException` vectors during blank dataset stages.

### Data Validation & Type Safety
* **Exception Interception Engines:** Interactive field forms wrap input conversions within robust exception capture pipelines (`try-catch` blocks). String-to-number parsing sequences explicitly trap `NumberFormatException` hazards to isolate bad data formats from changing background values.
* **Double-Precision Financial Modeling:** Implements double-precision floating-point arithmetic throughout the revenue module to track financial indicators cleanly across multiple channels without truncation errors.

### Persistent Stream Architecture
* **Transactional State Audit Trails:** Implements sequential data tracking by streaming append-only text logs (`FileWriter` in append mode). Actions write action-prefixed tracking strings (`SOLD | ...`, `STOCK UPDATED | ...`) directly to plain text databases.
* **Flat-File Synchronization:** Ensures that running changes on the user interface are synchronized immediately with underlying file logs, maintaining permanent audit histories for all physical inventory updates.

---

## 📊 Technical Stack Profile

| Architectural Layer | Core Component & API Implementations |
| :--- | :--- |
| **Graphical Interface (GUI)** | `JFrame`, `JPanel`, `JButton`, `JTextField`, `JTextArea`, `JScrollPane`, `JOptionPane`, `GridLayout` Grid Matrix, Absolute Location Engines (`setLayout(null)`) |
| **Data Persistence Engine** | Java File I/O Frameworks, `FileWriter` System Buffers, `FileReader`, `BufferedReader` Sequential Access Lines |
| **Software Design Patterns** | Single Inheritance, Abstract Base Architecture, Data Encapsulation, State Mutation Protection Guards, MVC Structural Separation |
