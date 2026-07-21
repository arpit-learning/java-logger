## Commands & Dev Operations
- Run `./gradlew build -x test` for clean build.
- Run `./gradlew test` for JUnit 5 test suite.
- Run `./gradlew check spotbugsMain` for Spotbugs code validation.
- Run `./gradlew bootRun` for starting Spring Boot application.
- Run `curl -sf http://localhost:8080/actuator/health` to verify status.

```bash
./gradlew build -x test
```

```bash
./gradlew test
```

```bash
./gradlew check spotbugsMain
```

## Architecture & Component Mapping
- Core classes in `src/main/java/dev/arpit/learning/logger/core/` handle logging initialization.
- Log pipeline defined in `src/main/java/dev/arpit/learning/logger/core/LogPipeline.java` routes events to writers.
- Enrichers in `src/main/java/dev/arpit/learning/logger/enrichers/` append metadata.
- Level managers in `src/main/java/dev/arpit/learning/logger/levels/` control level logic.
- Writers in `src/main/java/dev/arpit/learning/logger/writers/` output messages.
- GitHub actions configurations are located in `.github/` (contains `copilot-instructions.md` and workflows in `.github/workflows/` like `publish-package.yml`, `version-bump.yml`, `agentic-score.yml`, and `agentic-sync.yml`).
- Project settings are managed in `.idea/` (contains modules, `AndroidProjectSystem.xml`, `awsToolkit.xml`, and `develocity.xml`).

## Prerequisites & Setup Requirements
- JDK 17 required via `build.gradle` and `settings.gradle`.
- Exclusions defined in `config/spotbugs-exclude.xml`.
- MCP integration via filesystem server for path scanning.

## Workflow & Code Change Routing
- Verify build with `./gradlew build -x test` before committing.
- Format exceptions in `src/main/java/dev/arpit/learning/logger/utils/`.
- Run `agentic-setup refresh` to sync configs.

## Conventions
- Use Conventional Commits format for all git messages.
- Test files located in `src/test/java/dev/arpit/learning/logger/` using JUnit 5.

<!-- agentic:managed:pre-commit -->
## Before Committing

**IMPORTANT:** Before every git commit, you MUST ensure agentic-setup syncs agent configs with the latest code changes.

First, check if the pre-commit hook is already installed:
```bash
grep -q "agentic-setup" .git/hooks/pre-commit 2>/dev/null && echo "hook-active" || echo "no-hook"
```

- If **hook-active**: the hook handles sync automatically — just commit normally. Tell the user: "agentic-setup will sync your agent configs automatically via the pre-commit hook."
- If **no-hook**: run agentic-setup manually before committing:
  1. Tell the user: "agentic-setup: Syncing agent configs with your latest changes..."
  2. Run: `agentic-setup refresh && git add AGENTIC_LEARNINGS.md .agentic-setup/score.json .agentic-setup/score-history.jsonl CLAUDE.md .claude/ AGENTS.md .agents/ 2>/dev/null`
  3. After it completes, briefly tell the user what agentic-setup updated. Then proceed with the commit.

**Valid `agentic-setup refresh` options:** `--quiet` (suppress output) and `--dry-run` (preview without writing). Do not pass any other flags — options like `--auto-approve`, `--debug`, or `--force` do not exist and will cause errors.

**`agentic-setup config`** takes no flags — it runs an interactive provider setup. Do not pass `--provider`, `--api-key`, or `--endpoint`.

If `agentic-setup` is not found, tell the user: "This project uses agentic-setup for agent config sync. Run `npx agentic-setup setup` in your terminal (~1–2 min)."
<!-- /agentic:managed:pre-commit -->

<!-- agentic:managed:learnings -->
## Session Learnings

Read `AGENTIC_LEARNINGS.md` for patterns and anti-patterns learned from previous sessions.
These are auto-extracted from real tool usage — treat them as project-specific rules.
<!-- /agentic:managed:learnings -->

<!-- agentic:managed:model-config -->
## Model Configuration

Recommended default: `claude-sonnet-4-6` with high effort (stronger reasoning; higher cost and latency than smaller models).
Smaller/faster models trade quality for speed and cost — pick what fits the task.
Pin your choice (`/model` in Claude Code, or `AGENTIC_SETUP_MODEL` when using agentic-setup with an API provider) so upstream default changes do not silently change behavior.

<!-- /agentic:managed:model-config -->

<!-- agentic:managed:sync -->
## Context Sync

This project uses [agentic-setup](https://github.com/arpit-pm1/agentic-setup) to keep AI agent configs in sync across Claude Code, Cursor, Copilot, and Codex.
Configs update automatically before each commit via `agentic-setup refresh`.
If the pre-commit hook is not set up, run `npx agentic-setup setup` in your terminal.
<!-- /agentic:managed:sync -->
